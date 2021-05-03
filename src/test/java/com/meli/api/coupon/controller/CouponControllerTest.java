package com.meli.api.coupon.controller;

import com.meli.api.coupon.dto.ItemDTO;
import com.meli.api.coupon.dto.RequestDTO;
import com.meli.api.coupon.dto.ResponseDTO;
import com.meli.api.coupon.exception.APIServiceException;
import com.meli.api.coupon.service.CouponServiceImpl;
import com.meli.api.coupon.service.ICouponService;
import com.meli.api.coupon.service.IItemService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CouponControllerTest {

    @Autowired
    private CouponController couponController;
    @Autowired
    ICouponService couponService;
    @MockBean
    private IItemService itemService;
    private final List<ItemDTO> itemList = new ArrayList<>();
    private final List<String> favoriteItems = new ArrayList<>();

    @BeforeAll
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        itemList.add(new ItemDTO("MLA1",100f));
        itemList.add(new ItemDTO("MLA2",210f));
        itemList.add(new ItemDTO("MLA3",260f));
        itemList.add(new ItemDTO("MLA4",80f));
        itemList.add(new ItemDTO("MLA5",90f));

        favoriteItems.add("MLA1");
        favoriteItems.add("MLA2");
        favoriteItems.add("MLA3");
        favoriteItems.add("MLA4");
        favoriteItems.add("MLA5");

        couponService = new CouponServiceImpl(itemService);
    }

    @Test
    void returnSuggestedItems(){
        Float amount = 500f;
        RequestDTO requestDTO = new RequestDTO(favoriteItems, amount);
        when(itemService.findItemsById(favoriteItems)).thenReturn(itemList);
        ResponseEntity<ResponseDTO> responseDTO = couponController.getSuggestedItems(requestDTO);
        String[] expectedList = {"MLA1","MLA2","MLA4","MLA5"};
        Float expectedAmount = 480f;
        HttpStatus expectedHttpStatus = HttpStatus.OK;
        assertThat(responseDTO.getBody().getItem_ids()).containsExactlyInAnyOrder(expectedList);
        assertEquals(expectedAmount, responseDTO.getBody().getTotal());
        assertEquals(expectedHttpStatus, responseDTO.getStatusCode());
    }

    @Test
    void insufficientCouponAmount(){
        Float amount = 50f;
        RequestDTO requestDTO = new RequestDTO(favoriteItems, amount);
        when(itemService.findItemsById(favoriteItems)).thenReturn(itemList);
        Exception exception = assertThrows(APIServiceException.class, () -> {
            ResponseEntity<ResponseDTO> responseDTO = couponController.getSuggestedItems(requestDTO);
        });
        String expectedMessage = "Insufficient coupon amount: User cannot buy at least one item.";
        assertEquals(expectedMessage, exception.getMessage());
    }

}
