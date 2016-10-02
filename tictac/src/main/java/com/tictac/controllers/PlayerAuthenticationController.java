package com.tictac.controllers;

/**
 * Created by petar on 9/30/2016.
 */
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tictac.DTO.PlayerDTO;
import com.tictac.services.AuthenticationService;

@Controller
public class PlayerAuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * This method returns Login page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        return "views/userAuthentication/login";
    }

    /**
     * This method returns Registration page.
     */
    @RequestMapping(value = "/player/registration", method = RequestMethod.GET)
    public ModelAndView getRegistrationForm() {

        return new ModelAndView("views/userAuthentication/registration", "player", new PlayerDTO());
    }

    /**
     * This method register user if the registration is successful the user is
     * going to be redirect to login page else the registration is going to be
     * rendered along with the errors.
     */
    @RequestMapping(value = "/player/registration", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("player") @Valid PlayerDTO userDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "views/userAuthentication/registration";
        } else {
            return authenticationService.registerUser(userDTO, result);
        }
    }
}

