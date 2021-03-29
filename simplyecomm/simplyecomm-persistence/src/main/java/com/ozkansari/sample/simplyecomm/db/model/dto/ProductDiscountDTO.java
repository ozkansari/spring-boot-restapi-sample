package com.ozkansari.sample.simplyecomm.db.model.dto;

import java.math.BigDecimal;
import java.util.Optional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProductDiscountDTO {

    private String serialNo;
    
    private String name;
    
    private BigDecimal unitPrice;
    
    private Optional<Integer> discountItemCount;
    
    private Optional<BigDecimal> discountPrice;

    public ProductDiscountDTO(String serialNo, String name, BigDecimal unitPrice, Integer discountItemCount,
            BigDecimal discountPrice) {
        super();
        this.serialNo = serialNo;
        this.name = name;
        this.unitPrice = unitPrice;
        this.discountItemCount = Optional.ofNullable(discountItemCount);
        this.discountPrice = Optional.ofNullable(discountPrice);
    }
    
    /* --------------------------------------------------- */
    /* BUILDER METHOD(S) */
    /* --------------------------------------------------- */
    
    public static ProductDiscountDTO builder() {
        return new ProductDiscountDTO();
    }
    
    public ProductDiscountDTO serialNo(String serialNo) {
        this.serialNo = serialNo;
        return this;
    }
    
    public ProductDiscountDTO name(String name) {
        this.name = name;
        return this;
    }
    
    public ProductDiscountDTO unitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }
    
    public ProductDiscountDTO discountItemCount(Integer discountItemCount) {
        this.discountItemCount = Optional.ofNullable(discountItemCount);
        return this;
    }
    
    public ProductDiscountDTO discountPrice(BigDecimal discountPrice) {
        this.discountPrice = Optional.ofNullable(discountPrice);
        return this;
    }
    
    public ProductDiscountDTO build() {
        return this;
    }
    
}
