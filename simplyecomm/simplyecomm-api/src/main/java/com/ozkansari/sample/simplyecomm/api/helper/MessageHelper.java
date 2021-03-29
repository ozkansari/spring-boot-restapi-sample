package com.ozkansari.sample.simplyecomm.api.helper;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageHelper {

    private final ResourceBundleMessageSource messageSource;

    @Autowired
    private MessageHelper(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Converts message code to current locale. Locale is set from Accept-Language value in header
     * @param msgCode message code as specified in messages_XX.properties file
     * @return translated message in current locale
     */
    public String toLocale(String msgCode) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgCode, null, locale);
    }
}
