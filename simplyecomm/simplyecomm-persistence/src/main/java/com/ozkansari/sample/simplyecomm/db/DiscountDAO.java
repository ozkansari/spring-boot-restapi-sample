package com.ozkansari.sample.simplyecomm.db;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ozkansari.sample.simplyecomm.db.model.Discount;
import com.ozkansari.sample.simplyecomm.db.model.dto.ProductDiscountDTO;

/** Spring data JpaRepository instance for {@link Discount} entity */
@Transactional
@Repository
public interface DiscountDAO extends CrudRepository<Discount, Long> {

    public static final String PRODUCT_DISCOUNT_DTO = "com.ozkansari.sample.simplyecomm.db.model.dto.ProductDiscountDTO";

    @Query("SELECT new "
            + PRODUCT_DISCOUNT_DTO
            + "("
            + " p.serialNo, p.name, p.unitPrice, d.itemCount, d.discountPrice"
            + ")"
            + " FROM Discount d "
            + "RIGHT OUTER JOIN d.product p "
            + "WHERE p.serialNo in :serialNoSet")
    List<ProductDiscountDTO> findAllProductAndDiscountBySerialNos(@Param("serialNoSet") Set<String> serialNoSet);

}
