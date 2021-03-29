package com.ozkansari.sample.simplyecomm.api.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CheckoutResponseDto {

    @Schema(
        required = true, 
        example = "360", 
        name = "price", 
        description = "Total Checkout Price" 
    )
    private BigDecimal price;

    public CheckoutResponseDto(BigDecimal price) {
        super();
        this.price = price;
    }
}
