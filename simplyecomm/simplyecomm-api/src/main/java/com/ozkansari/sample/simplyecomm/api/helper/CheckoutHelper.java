package com.ozkansari.sample.simplyecomm.api.helper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ozkansari.sample.simplyecomm.db.model.dto.ProductDiscountDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class CheckoutHelper {

    private CheckoutHelper() {
        // Prevent instance creation
        // Private utility class constructor
    }
    
	/**
	 * Calculates checkout total price from product and discount data and checkout item counts given
	 * 
	 * @param orderCountsBySerialNo  "Product Serial no" to "Item Count" mapping that shows how many items are checked-out
	 * @param productAndDiscountData  list of data for Product Serial nos in orderCountsBySerialNo
	 * @return
	 */
    public static BigDecimal calculateCheckoutTotalPrice(Map<String, Long> orderCountsBySerialNo,
            List<ProductDiscountDTO> productAndDiscountData) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ProductDiscountDTO record : productAndDiscountData) {
            
            int orderCount = orderCountsBySerialNo.get(record.getSerialNo()).intValue();

            int normalItemCount;
            if( record.getDiscountItemCount().isPresent() ) {
                int countToApplyDiscount = record.getDiscountItemCount().get();
                int numberOfDiscountsEarned = orderCount / countToApplyDiscount;
                BigDecimal discountProductsTotalPrice = record.getDiscountPrice().get()
                        .multiply(new BigDecimal(numberOfDiscountsEarned));
                totalPrice = totalPrice.add(discountProductsTotalPrice);
                normalItemCount = orderCount % countToApplyDiscount;
                
                log.trace("{} discounts earned for product with {} serial no", numberOfDiscountsEarned, record.getSerialNo());
                
            } else {
                normalItemCount = orderCount;
            }

            BigDecimal normalProductsTotalPrice = record.getUnitPrice().multiply(new BigDecimal(normalItemCount));
            totalPrice = totalPrice.add(normalProductsTotalPrice);
        }
        return totalPrice;
    }
}
