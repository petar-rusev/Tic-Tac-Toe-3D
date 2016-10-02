package com.tictac.security;

/**
 * Created by petar on 9/30/2016.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private DataSource dataSource;

    @Autowired
    private StandardPasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new AjaxAwareLoginUrlAuthenticationEntryPoint("/login"))
                .and()
                .authorizeRequests()
                    .antMatchers("/").authenticated()
                    .antMatchers("/game/**").authenticated()
                    .antMatchers("/player-panel").authenticated()
                    .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login")
                    .defaultSuccessUrl("/home")
                    .permitAll()
                .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login").permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web
            .ignoring()
            .antMatchers("/scripts/**")
            .antMatchers("/styles/**")
            .antMatchers("/images/**");
    }

}
