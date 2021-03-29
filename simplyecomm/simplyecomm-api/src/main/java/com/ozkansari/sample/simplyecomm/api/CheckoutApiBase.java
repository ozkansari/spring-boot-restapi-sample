package com.ozkansari.sample.simplyecomm.api;

import com.ozkansari.sample.simplyecomm.api.dto.CheckoutResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Checkout Api Base interface provides Swagger api documentations
 */
@Tag(description = "Simplified Ecommerce - Checkout API", name = "checkout-api")
public interface CheckoutApiBase {

    @Operation(
        summary = "perform-checkout", 
        description = "Endpoint for checkout action which takes a list of watches and return the total cost."
    )
    public CheckoutResponseDto performCheckout(
        @Schema(
            name = "serialNos" ,
            example = "[  \"001\",  \"002\",  \"001\",  \"004\",  \"003\"]",
            description = " Product Serial Nos (e.g. watch IDs) are given as check out request input"
        )
        String[] watchIdList
    );

}
