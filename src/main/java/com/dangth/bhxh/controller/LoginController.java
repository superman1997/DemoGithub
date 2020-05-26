package com.dangth.bhxh.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class LoginController {

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public String login(Principal principal){
        return principal == null ? "/login" : "redirect:/";
    }


}