package com.ozkansari.sample.simplyecomm.api.dto;

import org.junit.jupiter.api.Test;

import com.openpojo.reflection.filters.FilterPackageInfo;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

/**
 * Tests Dto's in POJO_PACKAGE 
 */
public class DtoPojoUnitTest {

	  // The package to test
	  private static final String POJO_PACKAGE = "com.ozkansari.sample.simplyecomm.api.dto";

	  @Test
	  public void testPojoStructureAndBehavior() {
	    Validator validator = ValidatorBuilder.create()
	                            .with(new GetterMustExistRule())
	                            .with(new SetterMustExistRule())
	                            .with(new SetterTester())
	                            .with(new GetterTester())
	                            .build();

	    validator.validateRecursively(POJO_PACKAGE, new FilterPackageInfo());
	  }
}
