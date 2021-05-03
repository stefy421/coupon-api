package com.meli.api.coupon.service;

import com.meli.api.coupon.dto.ItemDTO;
import com.meli.api.coupon.repository.IItemRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Service to access to the item repository
 *
 * @author stefanny.rodriguez
 *
 */
@Service
public class ItemServiceImpl implements IItemService {

    private final IItemRepository itemRepository;

    public ItemServiceImpl(IItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemDTO> findItemsById(List<String> items){
        return itemRepository.findItemsByIds(new HashSet<>(items));
    }
}
