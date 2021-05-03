package com.meli.api.coupon.service;

import com.meli.api.coupon.dto.ItemDTO;
import com.meli.api.coupon.dto.RequestDTO;
import com.meli.api.coupon.dto.ResponseDTO;
import com.meli.api.coupon.exception.APIServiceException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service implementation to calculate the of items that can be purchased out of a favorite item list and a coupon amount.
 *
 * @author stefanny.rodriguez
 *
 */
@Service
public class CouponServiceImpl implements ICouponService {
    private final IItemService itemService;
    private final HashMap<String, Float> map = new HashMap<>();

    public CouponServiceImpl(IItemService itemService){
        this.itemService = itemService;
    }

    @Override
    public ResponseDTO getSuggestedItems(RequestDTO request) {
        List<ItemDTO> items = itemService.findItemsById(request.getItem_ids());

        if(items.stream().anyMatch(item -> item.getPrice() < request.getAmount())) {
            Map<String, Float> itemsMap = items.stream()
                    .collect(Collectors.toMap(ItemDTO::getId, ItemDTO::getPrice, (id, price) -> id));
            return calculate(itemsMap, request.getAmount());
        }
        throw new APIServiceException("Insufficient coupon amount: User cannot buy at least one item.");
    }

    /**
     * This method calculates from the item list and the coupon amount, which items can be purchased
     * trying to spend as much as possible of the coupon amount without exceeding it
     * .
     * @param items user's favorite item list
     * @param amount coupon amount
     * @return {@link ResponseDTO} the API response, which is composed of the suggested list of items to purchase
     *                              and the total purchase
     */
    private ResponseDTO calculate(Map<String, Float> items, Float amount){
        //Convert items map in prices and id arrays, in order to iterate over each of them
        Float[] prices = items.values().toArray(new Float[0]);
        String[] ids = items.keySet().toArray(new String[0]);
        int pos = prices.length - 1;
        Float total = getTotal(pos, amount, prices);
        List<String> selectedItems = getSelectedItems(map, prices, ids, amount);
        BigDecimal bd = new BigDecimal(Float.toString(total));
        total = bd.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        return new ResponseDTO(selectedItems,total);
    }

    /**
     * This recursive method calculates the total purchase based on the coupon amount and the price list of the
     * favorite list items. The idea is to get the maximum purchase value without exceeding the coupon amount.
     * @param pos variable to iterate over the price list
     * @param amount coupon amount
     * @param prices price list
     * @return the purchase total
     */
    private Float getTotal(int pos, Float amount, Float[] prices){
        if(pos < 0 || amount == 0) {
            return 0f;
        }
        String key = pos + "-" + amount;
        if(map.containsKey(key)) {
            return map.get(key);
        }
        if(prices[pos] > amount) {
            map.put(key, getTotal(pos - 1, amount, prices));
        }else{
            Float include = Float.sum(getTotal(pos - 1, amount - prices[pos], prices), prices[pos]);
            Float exclude = getTotal(pos - 1, amount, prices);
            map.put(key, Math.max(include, exclude));
        }
        return map.get(key);
    }

    /**
     * This method uses the map object populated by the getTotal() method to get the selected items
     * @param map which has the keys and values to calculate the total purchase
     * @param prices item's price list
     * @param items user's favorite item list
     * @param amount coupon amount
     * @return selected item list
     */
    private List<String> getSelectedItems(HashMap<String, Float> map, Float[] prices, String[] items, Float amount) {
        List<String> itemList = new ArrayList<>();
        String key = prices.length - 1 + "-" + amount;
        Float total = map.get(key);
        for (int i = prices.length - 1; i > 0; i--) {
            key = i-1 + "-" + amount;
            if (!total.equals(map.get(key))) {
                itemList.add(items[i]);
                amount -= prices[i];
                total -= prices[i];
            }
        }
        if(total != 0) {
            itemList.add(items[0]);
        }
        return itemList;
    }
}
