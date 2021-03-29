package com.ozkansari.sample.simplyecomm.api;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ozkansari.sample.simplyecomm.api.dto.CheckoutResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(CheckoutApi.API_CHECKOUT_BASE_URL)
public class CheckoutApi implements CheckoutApiBase {

    public static final String API_CHECKOUT_BASE_URL = "/checkout";
    
    @Override
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public CheckoutResponseDto performCheckout(@RequestBody String[] serialNos) {
        
        if (log.isTraceEnabled()) {
            log.trace("performCheckout started. request: {}", String.join(",", serialNos));
        }
        
        // TODO Calculate total price
        
        BigDecimal totalPrice = BigDecimal.ZERO;
        
        log.trace("performCheckout completed. Total Price: {}", totalPrice);
        
        return new CheckoutResponseDto(totalPrice); 
    }

}
