package com.tictac.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by petar on 9/30/2016.
 */
@Controller
public class GameController {
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String game() {
        return "views/index";
    }
}
