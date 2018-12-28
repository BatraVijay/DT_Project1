package com.frontend.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.backend.daos.CategoryDaos;
import com.backend.daos.UserDao;
import com.backend.models.Category;
import com.backend.models.User;
import com.backend.validators.MyPasswordValidator;

@Controller
public class PageController {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	CategoryDaos categoryDao;
	
	@Autowired
	MyPasswordValidator myPasswordValidator;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping(value={"/","/home"},method=RequestMethod.GET)
    public String getHomePage()
	{
		List<Category> catList=categoryDao.getAllCategories();
		session.setAttribute("categories",catList);
   
		Principal p=request.getUserPrincipal();
		
		
		if(p!=null){
			
		System.out.println( "principal is active");
		String email=p.getName();
		User userObj=userDao.getUserById(email);
		
		session.setAttribute("userEmail", email);
		session.setAttribute("userObject",userObj);
			
		}
		else {
			System.out.println( "principal is not active");
		}
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
    public ModelAndView getSignUpForm(){
    	ModelAndView mv=new ModelAndView("SignUpForm");
    	mv.addObject("userObj",new User());
        return mv;
    }
     
    @RequestMapping(value="/registerUser",method=RequestMethod.POST)
    public ModelAndView addUser(@Valid @ModelAttribute("userObj")User user,
    	BindingResult result,ModelMap map){

    	
    	myPasswordValidator.validate(user, result);
    	if(result.hasErrors()){
    		ModelAndView mv=new ModelAndView("SignUpForm");
    		return mv;
    	}
    	
    	userDao.registerUser(user);
    
    	
    	ModelAndView mv=new ModelAndView("LoginForm");
    	mv.addObject("message","User Registered Succesfully.. Now u can login");
        return mv;
    }
    
    

    @RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView getLoginForm(@RequestParam(name="error",required=false)String error,
			@RequestParam(name="logout",required=false)String logout){
		System.out.println("I m Here in getLoginForm method");
		
		ModelAndView mv=new ModelAndView("LoginForm");
		if(error!=null){
			//when error will come
			mv.addObject("message","Email or password is incorrect");
		}
		if(logout!=null){
			//when error will come
			mv.addObject("message","User has been logged out succesfully");
		}
		return mv;
	}
    
@RequestMapping(value="/perform-logout")
public String logout(HttpServletRequest request,HttpServletResponse response) {
	
	//first we are going to fetch the authentication
	Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	if(auth!=null){
		new SecurityContextLogoutHandler().logout(request, response, auth);
		
	}
	return "redirect:/login?logout";
}

	
	@RequestMapping(value="/access-denied",method=RequestMethod.GET)
	public String getAccessDenied(){
		
		return "access-denied";
	}



}