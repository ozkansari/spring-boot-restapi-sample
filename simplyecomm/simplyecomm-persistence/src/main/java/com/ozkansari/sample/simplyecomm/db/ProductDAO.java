package com.ozkansari.sample.simplyecomm.db;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ozkansari.sample.simplyecomm.db.model.Product;

/** Spring data JpaRepository instance for {@link Product} entity */
@Transactional
@Repository
public interface ProductDAO extends CrudRepository<Product, Long> {

}
