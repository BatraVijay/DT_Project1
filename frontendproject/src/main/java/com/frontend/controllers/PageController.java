package com.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
	@RequestMapping(value={"/","home"},method=RequestMethod.GET)
	 public String getHomePage(){
        return "Home";
    }
     
    @RequestMapping(value="/aboutUs",method=RequestMethod.GET)
    public String getAboutUsPage(){
        return "AboutUs";
    }
     
    @RequestMapping(value="/contactUs",method=RequestMethod.GET)
    public String getContactUsPage(){
        return "ContactUs";
    }
     
    @RequestMapping(value="/signUpForm",method=RequestMethod.GET)
    public String getSignUpForm(){
        return "SignUpForm";
    }
     
    @RequestMapping(value="/loginForm",method=RequestMethod.GET)
    public String getLoginForm(){
        return "LoginForm";
    }
}
