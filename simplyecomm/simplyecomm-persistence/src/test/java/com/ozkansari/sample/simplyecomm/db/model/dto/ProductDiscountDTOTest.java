package com.ozkansari.sample.simplyecomm.db.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;



public class ProductDiscountDTOTest {

	private ProductDiscountDTO createTestSubject() {
		return new ProductDiscountDTO("999","Product-999",new BigDecimal("999.99"),2,new BigDecimal("1500"));
	}

	@Test
	public void testBuilder() throws Exception {
		ProductDiscountDTO result = ProductDiscountDTO.builder();
		assertNotNull(result);
	}

	@Test
	public void testSerialNo() throws Exception {
		ProductDiscountDTO testSubject = createTestSubject();
		String serialNo = "987";
		ProductDiscountDTO result;

		testSubject = createTestSubject();
		result = testSubject.serialNo(serialNo);
		assertNotNull(result);
		assertEquals(serialNo, result.getSerialNo());
	}

	@Test
	public void testName() throws Exception {
		ProductDiscountDTO testSubject = createTestSubject();
		String name = "";
		ProductDiscountDTO result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.name(name);
		assertNotNull(result);
	}

	@Test
	public void testUnitPrice() throws Exception {
		ProductDiscountDTO testSubject = createTestSubject();
		BigDecimal unitPrice = null;
		ProductDiscountDTO result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.unitPrice(unitPrice);
		assertNotNull(result);
	}

	@Test
	public void testDiscountItemCount() throws Exception {
		ProductDiscountDTO testSubject = createTestSubject();
		Integer discountItemCount = 0;
		ProductDiscountDTO result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.discountItemCount(discountItemCount);
		assertNotNull(result);
	}

	@Test
	public void testDiscountPrice() throws Exception {
		ProductDiscountDTO testSubject = createTestSubject();
		BigDecimal discountPrice = null;
		ProductDiscountDTO result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.discountPrice(discountPrice);
		assertNotNull(result);
	}

	@Test
	public void testBuild() throws Exception {
		ProductDiscountDTO testSubject = createTestSubject();
		ProductDiscountDTO result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.build();
		assertNotNull(result);
	}

	@Test
	public void testGetSerialNo() throws Exception {
		ProductDiscountDTO testSubject= createTestSubject();
		String result = testSubject.getSerialNo();
		assertNotNull(result);
	}

	@Test
	public void testGetName() throws Exception {
		ProductDiscountDTO testSubject= createTestSubject();
		String result = testSubject.getName();
		assertNotNull(result);
	}

	@Test
	public void testGetUnitPrice() throws Exception {
		ProductDiscountDTO testSubject = createTestSubject();
		BigDecimal result = testSubject.getUnitPrice();
		assertNotNull(result);
	}

	@Test
	public void testGetDiscountItemCount() throws Exception {
		ProductDiscountDTO testSubject = createTestSubject();
		Optional<Integer> result = testSubject.getDiscountItemCount();
		assertNotNull(result);
	}

	@Test
	public void testGetDiscountPrice() throws Exception {
		ProductDiscountDTO testSubject = createTestSubject();
		Optional<BigDecimal> result = testSubject.getDiscountPrice();
		assertTrue(result.isPresent());
	}

	@Test
	public void testSetSerialNo() throws Exception {
		ProductDiscountDTO testSubject = createTestSubject();
		String serialNo = "987";
		testSubject.setSerialNo(serialNo);
		assertEquals(serialNo, testSubject.getSerialNo());
	}

	@Test
	public void testSetName() throws Exception {
		ProductDiscountDTO testSubject = createTestSubject();
		String name = "Product-X";
		testSubject.setName(name);
		assertEquals(name, testSubject.getName());
	}

	@Test
	public void testSetUnitPrice() throws Exception {
		ProductDiscountDTO testSubject = createTestSubject();
		BigDecimal unitPrice = new BigDecimal("100.00");
		testSubject.setUnitPrice(unitPrice);
		assertEquals(unitPrice, testSubject.getUnitPrice());
	}

	@Test
	public void testSetDiscountItemCount() throws Exception {
		ProductDiscountDTO testSubject = createTestSubject();
		Optional<Integer> discountItemCount = Optional.of(99);
		testSubject.setDiscountItemCount(discountItemCount);
		assertEquals(discountItemCount.get(), testSubject.getDiscountItemCount().get());
	}

	@Test
	public void testSetDiscountPrice() throws Exception {
		ProductDiscountDTO testSubject = createTestSubject();
		Optional<BigDecimal> discountPrice = Optional.of(new BigDecimal("9876.543"));
		testSubject.setDiscountPrice(discountPrice);
		assertEquals(discountPrice.get(), testSubject.getDiscountPrice().get());
	}
}