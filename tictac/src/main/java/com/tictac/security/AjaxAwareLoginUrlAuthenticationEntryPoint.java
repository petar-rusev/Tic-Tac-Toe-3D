package com.tictac.security;

/**
 * Created by petar on 9/30/2016.
 * Check for 403 in all AJAX error responses and if the error is 403 then reload the page
 * "window.location().reload()". When the reloading start the user will be redirect to the login page.
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.io.IOException;


public class AjaxAwareLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint{
    public AjaxAwareLoginUrlAuthenticationEntryPoint(String loginFormUrl){
        super(loginFormUrl);
    }

    public void commence(final HttpServletRequest request,final HttpServletResponse response,final AuthenticationException authException) throws IOException,ServletException{
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            response.sendError(HttpServletResponse.SC_FORBIDDEN,"Access Denied");
        }
        else{
            super.commence(request,response,authException);
        }
    }
}
