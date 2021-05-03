package com.meli.api.coupon.service;

import com.meli.api.coupon.dto.ItemDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ItemServiceTest {
    @Autowired
    private IItemService itemService;

    @Test
    void mercadoLibreAPIConnection() {
        String[] items = {"MCO453857398"};
        List<ItemDTO> itemDTOList = itemService.findItemsById(Arrays.asList(items));
        ItemDTO expectedItem = new ItemDTO("MCO453857398",79900f);
        assertEquals(itemDTOList.get(0).getId(), expectedItem.getId());
        assertEquals(itemDTOList.get(0).getPrice(), expectedItem.getPrice());
    }

    @Test
    void DuplicateItemsReturnsJustOne(){
        String[] items = {"MCO453857398","MCO453857398"};
        List<ItemDTO> itemDTOList = itemService.findItemsById(Arrays.asList(items));
        assertEquals(1, itemDTOList.size());
    }
}
