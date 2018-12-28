package com.frontend.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.backend.daos.CartDao;
import com.backend.daos.CategoryDaos;
import com.backend.daos.ItemDao;
import com.backend.daos.ProductDao;
import com.backend.daos.UserDao;
import com.backend.models.Cart;
import com.backend.models.Item;
import com.backend.models.Product;

@Controller
public class CartController {

	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	CartDao cartDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ItemDao itemDao;
	
	@RequestMapping(value="addToCart/{proId}",method=RequestMethod.GET)
	public String addToCart(@PathVariable("proId")int pId){
		Principal p=request.getUserPrincipal();
		String userEmail=p.getName();
		
		Cart cart=cartDao.getCartByCustomer(userEmail);
		if(cart==null){
			System.out.println("No Cart existed. we will create new cart ");
			cart=new Cart();
			cart.setCustomerId(userEmail);
			
			
			Product pro=productDao.getProduct(pId);
			Item itemObj=new Item();
			itemObj.setPrice(pro.getPrice());
			itemObj.setQuantity(1);
			itemObj.setProduct(pro);
			itemObj.setCustomerId(userEmail);
			itemObj.setCart(cart);
			
			cart.getItems().add(itemObj);
			
			cartDao.addCart(cart);
			itemDao.addItem(itemObj);
		}
		else {
			System.out.println("Cart already exist");
		
			Item itemObj=itemDao.getItemByProductIdAndCustomerId(pId, userEmail);
			if(itemObj==null){
				Product pro=productDao.getProduct(pId);
				itemObj=new Item();
				itemObj.setPrice(pro.getPrice());
				itemObj.setQuantity(1);
				itemObj.setProduct(pro);
				itemObj.setCustomerId(userEmail);
				itemObj.setCart(cart);
			
				cart.getItems().add(itemObj);
				
				itemDao.addItem(itemObj);
			
			}
			else {
				itemObj.setQuantity(itemObj.getQuantity()+1);
				itemDao.updateItem(itemObj);
			}
			 	
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value="viewCart",method=RequestMethod.GET)
	public ModelAndView viewCart(){
		Principal p=request.getUserPrincipal();
		String email=p.getName();
		
		Cart obj=cartDao.getCartByCustomer(email);
		List<Item> items=itemDao.getItemsListByCart(obj.getCartId());
		
		double total=0;
		for(Item item:items){
			double price=item.getPrice()*item.getQuantity();
			total=total+price;
		}
		
		
		ModelAndView mv=new ModelAndView("Cart");
		mv.addObject("cartItems",items);
		mv.addObject("cartObject",obj);
		mv.addObject("total",total);
		
		
		return mv;
		
	}
	
	@Autowired
	CategoryDaos categoryDao;
	
	@RequestMapping(value="removeItem/{itemId}",method=RequestMethod.GET)
	public ModelAndView remove(@PathVariable("itemId")int itemId){
			itemDao.deleteItem(itemId);
			
			Principal p=request.getUserPrincipal();
			String email=p.getName();
			
			Cart obj=cartDao.getCartByCustomer(email);
			List<Item> items=itemDao.getItemsListByCart(obj.getCartId());
			
			double total=0;
			for(Item item:items){
				double price=item.getPrice()*item.getQuantity();
				total=total+price;
			}
			
			
			ModelAndView mv=new ModelAndView("Cart");
			mv.addObject("cartItems",items);
			mv.addObject("cartObject",obj);
			mv.addObject("total",total);
			mv.addObject("categories",categoryDao.getAllCategories());
			
			return mv;
	}
}
