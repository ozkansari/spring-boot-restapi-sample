package com.ozkansari.sample.simplyecomm.testhelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ozkansari.sample.simplyecomm.db.model.dto.ProductDiscountDTO;

public class CommonTestBase {

    /* --------------------------------------------------- */
    /* HELPER METHOD(S) */
    /* --------------------------------------------------- */
    
    protected List<ProductDiscountDTO> createDefaultProductDiscountDTO() {
        List<ProductDiscountDTO> productAndDiscountData = new ArrayList<>();
        productAndDiscountData.add(createProductDiscountDTO("001","100",3,"200"));
        productAndDiscountData.add(createProductDiscountDTO("002", "80",2,"120"));
        productAndDiscountData.add(createProductDiscountDTO("003", "50"));
        productAndDiscountData.add(createProductDiscountDTO("004", "30"));
        return productAndDiscountData;
    }
    
    protected ProductDiscountDTO createProductDiscountDTO(String serialNo, String unitPrice) {
        return createProductDiscountDTO(serialNo, unitPrice, null, null);
    }
    
    protected ProductDiscountDTO createProductDiscountDTO(String serialNo, String unitPrice, Integer discountItemCount, String discountPrice) {
        return ProductDiscountDTO.builder()
                .serialNo(serialNo)
                .unitPrice(new BigDecimal(unitPrice))
                .discountPrice(discountPrice == null ? null : new BigDecimal(discountPrice))
                .discountItemCount(discountItemCount)
                .build();
    }
}
