package com.ozkansari.sample.simplyecomm.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ozkansari.sample.simplyecomm.db.model.dto.ProductDiscountDTO;

@DisplayName("DiscountDAO Integration Test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@EnableAutoConfiguration
@ContextConfiguration(classes = DiscountDAO.class)
@TestPropertySource(properties = { "spring.liquibase.change-log=" + DiscountDAOIntegrationTest.TEST_DATA })
class DiscountDAOIntegrationTest {

    /*--
        | Watch ID      | Watch Name    | Unit Price    | Discount      |
        | ------------- | ------------- | ------------- | ------------- |
        | 001           | Rolex         | 100           | 3 for 200     |
        | 002           | Michael Kors  |  80           | 2 for 120     |
        | 003           | Swatch        |  50           | N/A           |
        | 004           | Casio         |  30           | N/A           |
     */
    static final String TEST_DATA = "classpath:/db/changelog/db.changelog-master.xml";
    
    @Autowired
    private DiscountDAO discountDAO;
    
    @Test
    @DisplayName("Test Context Dependencies")
    void testContext() {
        assertNotNull(discountDAO);
    }
    
    @Test
    @DisplayName("findAllProductAndDiscountBySerialNos method -> Should Give Correct Result For Multiple Serial No Input")
    void findAllProductAndDiscountBySerialNosWithMultipleSerialNo() {

        // Given
        String [] serialNoArray = { "001", "002", "003", "004", "998", "999", "-1", "" };
        Set<String> serialNoSet = new HashSet<>(Arrays.asList(serialNoArray));
        
        // When
        List<ProductDiscountDTO> resultList = discountDAO.findAllProductAndDiscountBySerialNos(serialNoSet);
        
        // Then
        assertEquals(4, resultList.size());
        
        ProductDiscountDTO rolexWatch = resultList.get(0);
        assertEquals("001", rolexWatch.getSerialNo());
        assertEquals("Rolex", rolexWatch.getName());
        assertEquals(new BigDecimal("100.00"), rolexWatch.getUnitPrice().setScale(2));
        assertFalse(rolexWatch.getDiscountPrice().isEmpty());
        assertEquals(new BigDecimal("200.00"), rolexWatch.getDiscountPrice().get().setScale(2));
        assertFalse(rolexWatch.getDiscountItemCount().isEmpty());
        assertEquals(3, rolexWatch.getDiscountItemCount().get());

        ProductDiscountDTO michaelCorsWatch = resultList.get(1);
        assertEquals("002", michaelCorsWatch.getSerialNo());
        assertEquals("Michael Kors", michaelCorsWatch.getName());
        assertEquals(new BigDecimal("80.00"), michaelCorsWatch.getUnitPrice().setScale(2));
        assertFalse(michaelCorsWatch.getDiscountPrice().isEmpty());
        assertEquals(new BigDecimal("120.00"), michaelCorsWatch.getDiscountPrice().get().setScale(2));
        assertFalse(michaelCorsWatch.getDiscountItemCount().isEmpty());
        assertEquals(2, michaelCorsWatch.getDiscountItemCount().get());
        
        ProductDiscountDTO swatchWatch = resultList.get(2);
        assertEquals("003", swatchWatch.getSerialNo());
        assertEquals("Swatch", swatchWatch.getName());
        assertEquals(new BigDecimal("50.00"), swatchWatch.getUnitPrice().setScale(2));
        assertTrue(swatchWatch.getDiscountPrice().isEmpty());
        assertTrue(swatchWatch.getDiscountItemCount().isEmpty());
        
        ProductDiscountDTO casioWatch = resultList.get(3);
        assertEquals("004", casioWatch.getSerialNo());
        assertEquals("Casio", casioWatch.getName());
        assertEquals(new BigDecimal("30.00"), casioWatch.getUnitPrice().setScale(2));
        assertTrue(casioWatch.getDiscountPrice().isEmpty());
        assertTrue(casioWatch.getDiscountItemCount().isEmpty());
        
    }

}
