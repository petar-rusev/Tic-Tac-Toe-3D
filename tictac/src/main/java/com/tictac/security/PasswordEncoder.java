package com.tictac.security;

/**
 * Created by petar on 9/30/2016.
 * In this configuration class we create password encoder
 * which is used to encode passwords end decode passwords on login register event
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
public class PasswordEncoder {
    @Bean
    public StandardPasswordEncoder getPasswordEncoder(){
        return new StandardPasswordEncoder();
    }
}
