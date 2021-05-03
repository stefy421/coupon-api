package com.meli.api.coupon.service;

import com.meli.api.coupon.dto.ResponseDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CouponServiceTest {
    @Autowired
    private ICouponService couponService;
    private final Map<String, Float> itemList = new HashMap<>();
    private final Map<String, Float> itemListWithDecimals = new HashMap<>();

    @BeforeAll
    private void setUp(){
        itemList.put("MLA1",39800f);
        itemList.put("MLA2",11990f);
        itemList.put("MLA3",79000f);
        itemList.put("MLA4",38900f);
        itemList.put("MLA5",31900f);
        itemList.put("MLA6",79900f);

        itemListWithDecimals.put("MLA1",39.834f);
        itemListWithDecimals.put("MLA2",11.990f);
        itemListWithDecimals.put("MLA3",7.9453f);
        itemListWithDecimals.put("MLA4",38.976f);
        itemListWithDecimals.put("MLA5",31.458f);
        itemListWithDecimals.put("MLA6",79.5674f);
    }

    @Test
    void returnMaxPurchaseValueFromAPriceList() {
        Float[] prices = itemList.values().toArray(new Float[0]);
        int pos = prices.length - 1;
        Float amount = 200000f;
        Float total = ReflectionTestUtils.invokeMethod(couponService, "getTotal",pos,amount,prices);
        assertEquals(198700f, total);
    }

    @Test
    void returnSelectedItemForThePurchaseTotal(){
        Float amount = 200000f;
        ResponseDTO responseDTO = ReflectionTestUtils.invokeMethod(couponService, "calculate", itemList,amount);
        String[] expectedItems = {"MLA6","MLA3","MLA1"};
        assertThat(responseDTO.getItem_ids()).containsExactlyInAnyOrder(expectedItems);
    }

    @Test
    void returnTwoDecimalsTotalPurchaseFromLargeDecimalPrices(){
        Float amount = 210.8567f;
        ResponseDTO responseDTO = ReflectionTestUtils.invokeMethod(couponService, "calculate", itemListWithDecimals,amount);
        assertEquals(209.77f, responseDTO.getTotal());
    }
}
