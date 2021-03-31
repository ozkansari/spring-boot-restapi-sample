package com.ozkansari.sample.simplyecomm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SimplifiedEcommerceApiApplication {
    
	/**
	 * Runs Spring Boot application
	 * 
	 * @param args command line arguments 
	 * @throws Throwable
	 */
    public static void main(String[] args) throws Throwable {
        try {
            SpringApplication.run(SimplifiedEcommerceApiApplication.class, args);
        } catch (Throwable e) {
            handleThrowable(e);
        }
    }

    /**
     * This method handles exception and errors thrown from SpringApplication.run. 
     * SilentExitException that was thrown by Spring Boot Dev Tools are also catched and skipped here.
     * 
     * @param e any throwable 
     * @throws Throwable if not handled here 
     */
    protected static void handleThrowable(Throwable e) throws Throwable {
        if(e.getClass().getName().contains("SilentExitException")) {
            // skipping for spring known bug https://github.com/spring-projects/spring-boot/issues/3100
            log.warn("Spring is restarting the main thread - See spring-boot-devtools");
        } else {
            throw e;
        }
    }
    
}
