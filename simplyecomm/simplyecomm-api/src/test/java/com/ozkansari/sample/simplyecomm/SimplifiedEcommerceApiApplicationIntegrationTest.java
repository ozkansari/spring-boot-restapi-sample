package com.ozkansari.sample.simplyecomm;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class SimplifiedEcommerceApiApplicationIntegrationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @DisplayName("Bootstrap spring application and check context and beans")
    @Test
    void contextLoads() {
    	assertNotNull(applicationContext);

        // Check some beans
        BeanTester beanTester = new BeanTester(applicationContext);
        beanTester.displayBeanInfo();
        beanTester.checkIfBeanExists("simplifiedEcommerceApiApplication");
        beanTester.checkIfBeanExists("checkoutApi");
        beanTester.checkIfBeanExists("localizationConfig");
        beanTester.checkIfBeanExists("messageHelper");
        beanTester.checkIfBeanExists("restResponseEntityExceptionHandler");
        beanTester.checkIfBeanExists("discountDAO");
        beanTester.checkIfBeanExists("productDAO");
    }
    
    @DisplayName("Test handleThrowable With RuntimeException -> Should Throw it back")
    @Test
    void testHandleThrowableWhenRuntimeException() {
    	Assertions.assertThrows(RuntimeException.class, () -> {
    		SimplifiedEcommerceApiApplication.handleThrowable(new RuntimeException());
    	});
    }
    
    @DisplayName("Test handleThrowable With SilentExitException -> Should Ignore")
    @Test
    void testHandleThrowableWhenSilentExitException() {
    	boolean exceptionThrown = false;
    	try {
			SimplifiedEcommerceApiApplication.handleThrowable(new SilentExitException());
		} catch (Throwable e) {
			exceptionThrown = true;
		}
    	assertFalse(exceptionThrown);
    }
    
    /** Normally this is a private class in Spring. Dummy version created for testing purposes */
    private static class SilentExitException extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}
    
    private static class BeanTester {
        
        private List<String> beanDefinitionNames;
        private ApplicationContext applicationContext;
        
        private BeanTester(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
            beanDefinitionNames = Arrays.asList( applicationContext.getBeanDefinitionNames() );
        }
        
        private void checkIfBeanExists(String beanName) {
            assertThat(beanName + " exists", beanDefinitionNames, hasItem(beanName) );
        }
        
        private void displayBeanInfo() {
            log.info("*********************************************************");
            List<String> ourBeans = new ArrayList<>();
            List<String> otherBeans = new ArrayList<>();
            for (String beanName : beanDefinitionNames) {
                Object bean = applicationContext.getBean(beanName);
                String beanClassName = bean.getClass().getName();
                if (beanClassName.contains("com.ozkansari")) {
                    ourBeans.add(beanName + " > " + beanClassName);
                } else {
                    otherBeans.add(beanName + "  > " + beanClassName);
                }
            }
            log.info("OUR BEANS -----------------------------------------------");
            for (String ourBean : ourBeans) {
                log.info(ourBean);
            }
            log.info("OTHER BEANS ---------------------------------------------");
            for (String otherBean : otherBeans) {
                log.info(otherBean);
            }
            log.info("Actual Beans: {}, Our Beans: {}, Other Beans: {}",  beanDefinitionNames.size(), ourBeans.size(), otherBeans.size() );
            log.info("*********************************************************");
        }
    
    }
    
}
