package com.ozkansari.sample.simplyecomm.api;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ozkansari.sample.simplyecomm.api.dto.CheckoutResponseDto;
import com.ozkansari.sample.simplyecomm.api.helper.CheckoutHelper;
import com.ozkansari.sample.simplyecomm.db.DiscountDAO;
import com.ozkansari.sample.simplyecomm.db.model.dto.ProductDiscountDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(CheckoutApi.API_CHECKOUT_BASE_URL)
public class CheckoutApi implements CheckoutApiBase {

    public static final String API_CHECKOUT_BASE_URL = "/checkout";
    
    @Autowired
    private DiscountDAO discountDAO;
    
    @Override
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public CheckoutResponseDto performCheckout(@RequestBody String[] serialNos) {
        
        if (log.isTraceEnabled()) {
            log.trace("performCheckout started. request: {}", String.join(",", serialNos));
        }
 
        Map<String, Long> orderCountsBySerialNo = Arrays.stream(serialNos)
                .filter(sn -> sn != null && !sn.isBlank()) 
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        
        if (log.isTraceEnabled()) {
            String mapAsString = orderCountsBySerialNo.entrySet().stream()
                    .map(entry -> String.format("Product %s x %s", entry.getKey(), entry.getValue()))
                    .collect(Collectors.joining(", ", "{", "}"));
            log.trace("Checkout request summary : {}", mapAsString);
        }
        
        List<ProductDiscountDTO> results = discountDAO.findAllProductAndDiscountBySerialNos(orderCountsBySerialNo.keySet());

        BigDecimal totalPrice = CheckoutHelper.calculateCheckoutTotalPrice(orderCountsBySerialNo, results);
        
        log.trace("performCheckout completed. Total Price: {}", totalPrice);
        
        return new CheckoutResponseDto(totalPrice); 
    }

}
