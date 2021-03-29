package com.ozkansari.sample.simplyecomm.testhelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ozkansari.sample.simplyecomm.api.helper.CheckoutHelper;
import com.ozkansari.sample.simplyecomm.db.model.dto.ProductDiscountDTO;

@DisplayName("Checkout Helper Test")
class CheckoutHelperUnitTest extends CommonTestBase {

    @Test
    @DisplayName("Test Calculate Checkout Total Price with default data and no discount")
    final void testCalculateCheckoutTotalPriceWithDefaultDataAndNoDiscount() {
        // Given
        Map<String, Long> orderCountsBySerialNo = Map.of("001", 2L, "002", 1L, "003", 1L, "004", 1L);
        List<ProductDiscountDTO> productAndDiscountData = createDefaultProductDiscountDTO();
        
        // When
        BigDecimal totalPrice = CheckoutHelper.calculateCheckoutTotalPrice(orderCountsBySerialNo, productAndDiscountData);
        
        // Then
        assertEquals(new BigDecimal("360"), totalPrice);    
    }
    
    @Test
    @DisplayName("Test Calculate Checkout Total Price with default data and one discount in one item")
    final void testCalculateCheckoutTotalPriceWithDefaultDataAndOneDiscountInOneItem() {
        // Given
        Map<String, Long> orderCountsBySerialNo = Map.of("001", 3L, "002", 1L, "003", 1L, "004", 1L);
        List<ProductDiscountDTO> productAndDiscountData = createDefaultProductDiscountDTO();
        
        // When
        BigDecimal totalPrice = CheckoutHelper.calculateCheckoutTotalPrice(orderCountsBySerialNo, productAndDiscountData);
        
        // Then
        assertEquals(new BigDecimal("360"), totalPrice);    
    }
    
    @Test
    @DisplayName("Test Calculate Checkout Total Price with default data and two discounts in one item")
    final void testCalculateCheckoutTotalPriceWithDefaultDataAndTwoDiscountsInOneItem() {
        // Given
        Map<String, Long> orderCountsBySerialNo = Map.of("001", 2L, "002", 4L, "003", 1L, "004", 1L);
        List<ProductDiscountDTO> productAndDiscountData = createDefaultProductDiscountDTO();
        
        // When
        BigDecimal totalPrice = CheckoutHelper.calculateCheckoutTotalPrice(orderCountsBySerialNo, productAndDiscountData);
        
        // Then
        assertEquals(new BigDecimal("520"), totalPrice);    
    }
    
    @Test
    @DisplayName("Test Calculate Checkout Total Price with default data and two discounts in each of the two different items")
    final void testCalculateCheckoutTotalPriceWithDefaultDataAndTwoDiscountsInEachTwoItems() {
        // Given
        Map<String, Long> orderCountsBySerialNo = Map.of("001", 3L, "002", 2L, "003", 1L, "004", 1L);
        List<ProductDiscountDTO> productAndDiscountData = createDefaultProductDiscountDTO();
        
        // When
        BigDecimal totalPrice = CheckoutHelper.calculateCheckoutTotalPrice(orderCountsBySerialNo, productAndDiscountData);
        
        // Then
        assertEquals(new BigDecimal("400"), totalPrice);    
    }
    
    @Test
    @DisplayName("Test Calculate Checkout Total Price with default data and double discounts in both of the two different items")
    final void testCalculateCheckoutTotalPriceWithDefaultDataAndDoubleDiscountsInBothTwoItems() {
        // Given
        Map<String, Long> orderCountsBySerialNo = Map.of("001", 6L, "002", 4L, "003", 1L, "004", 1L);
        List<ProductDiscountDTO> productAndDiscountData = createDefaultProductDiscountDTO();
        
        // When
        BigDecimal totalPrice = CheckoutHelper.calculateCheckoutTotalPrice(orderCountsBySerialNo, productAndDiscountData);
        
        // Then
        assertEquals(new BigDecimal("720"), totalPrice);    
    }

}
