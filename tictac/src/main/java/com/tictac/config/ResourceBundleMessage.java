package com.tictac.config;

/**
 * Created by petar on 9/30/2016.
 */

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class ResourceBundleMessage {

    @Bean
    public MessageSource getMessageSourceBean(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:locale/messages");
        messageSource.setDefaultEncoding("UTF-8");

        messageSource.setCacheSeconds(0);
        return messageSource;
    }
}
