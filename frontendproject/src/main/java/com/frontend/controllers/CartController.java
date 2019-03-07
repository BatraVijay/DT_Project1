package com.frontend.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.backend.daos.AddressDao;
import com.backend.daos.CartDao;
import com.backend.daos.CategoryDaos;
import com.backend.daos.ItemDao;
import com.backend.daos.ProductDao;
import com.backend.daos.UserDao;
import com.backend.models.Address;
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
	
	@Autowired
	HttpSession session;
	
	@Autowired
	AddressDao addressDao;
	
	@Autowired
	CategoryDaos categoryDao;
	
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
	
	@RequestMapping(value="addToCart/viewCart",method=RequestMethod.GET)
	public ModelAndView viewCart(@RequestParam("uEmail")String email){
		
		
		Cart obj=cartDao.getCartByCustomer(email);
		
		
		List<Item> items=itemDao.getItemsListByCart(obj.getCartId());
		
		double total=0;
		for(Item item:items){
			double price=item.getPrice()*item.getQuantity();
			total=total+price;
		}
		
		
		ModelAndView mv=new ModelAndView("Cart");
		/*mv.addObject("cartItems",items);
		mv.addObject("cartObject",obj);
		mv.addObject("total",total);*/
		session.setAttribute("cartItems", items);
		session.setAttribute("cartObject",obj);
		session.setAttribute("total", total);
		
		return mv;
		
	}
	
	
	
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
	@RequestMapping(value="increaseQuantity/{itemId}",method=RequestMethod.GET)
	public ModelAndView increaseQuantityMethod(@PathVariable("itemId")int itemId){
		itemDao.increaseQuantity(itemId);
		
		Principal p=request.getUserPrincipal();
		String email=p.getName();
		
		Cart obj=cartDao.getCartByCustomer(email);
		List<Item> items=itemDao.getItemsListByCart(obj.getCartId());
		
		double total=0;
		for(Item item:items){
			double price=item.getPrice()*item.getQuantity();
			total=total+price;
		}
		
		session.setAttribute("cartItems", items);
		session.setAttribute("total",total);
		
		ModelAndView mv=new ModelAndView("Cart");
		
		
		return mv;
	}
	
	
	@RequestMapping(value="decreaseQuantity/{itemId}",method=RequestMethod.GET)
   public ModelAndView decreaseQuantityMethod(@PathVariable("itemId") int itemId)
	{
       boolean res=  itemDao.decreaseQuantity(itemId);
      if(res==false){
    	  itemDao.deleteItem(itemId);
      }
		
		Principal p=request.getUserPrincipal();
		String email=p.getName();
		
		Cart obj=cartDao.getCartByCustomer(email);
		List<Item> items=itemDao.getItemsListByCart(obj.getCartId());
		
		double total=0;
		for(Item item:items){
			double price=item.getPrice()*item.getQuantity();
			total=total+price;
		}
		
		session.setAttribute("cartItems", items);
		session.setAttribute("total",total);
		
		ModelAndView mv=new ModelAndView("Cart");
		
		
		return mv;
	}
	
	
	@RequestMapping(value="/continueShopping",method=RequestMethod.GET)
	public String continueShopping()
	{
		return "redirect:/home";
	}
	
	@RequestMapping(value="/checkout",method=RequestMethod.GET)
	public ModelAndView checkout()
	{
		
		Principal p=request.getUserPrincipal();
		String email=p.getName();

		
		List<Address> address=addressDao.getAddressForUser(email);
		System.out.println("Address : "+address);
		if(address.size()==0)
		{
			System.out.println("No address Found");
			ModelAndView mv=new ModelAndView("Address");
			mv.addObject("addressObj",new Address());
			mv.addObject("formLabel","Add Address");
			mv.addObject("btnLabel","Add Address");
			return mv;
		}
		else
		{
			System.out.println("Address found");
			ModelAndView mv=new ModelAndView("ViewAddresses");
			mv.addObject("addressList",address);
			return mv;
		}
		
		
	}
	
	
	@RequestMapping(value="addAddress", method=RequestMethod.GET)
	public ModelAndView getProductForm()
	{

		Principal p=request.getUserPrincipal();
		String email=p.getName();
		 
		ModelAndView mv=new ModelAndView("Address");
		
		
		Address a=new Address();
		mv.addObject("addressObj", a );
		
	        List<Address> address=addressDao.getAddressForUser(email);
		     
		
		mv.addObject("addressList",address);
		
		mv.addObject("formLabel","Address");
		mv.addObject("btnLabel","Add Address");
		return mv;
		
	}
	
	
	
   @RequestMapping(value="addAddressProcess",method=RequestMethod.POST)
   public ModelAndView addAddressProcess(@ModelAttribute("addressObj")Address addressObj)
   {
	
	   Principal p=request.getUserPrincipal();
		String email=p.getName();
	  
		addressObj.setUser(userDao.getUserById(email));
	
		addressDao.insertAddress(addressObj);
		
		ModelAndView mv=new ModelAndView("ViewAddresses");
	   List<Address>address=addressDao.getAddressForUser(email);
	   mv.addObject("addressList",address);
	       
	   return mv;
	   
   }
   
   @RequestMapping(value="showOrder/{addrId}",method=RequestMethod.GET)
   public ModelAndView showOrderPage(@PathVariable("addrId")int addrId)
   {
	   Address addr=addressDao.getAddressById(addrId);
	   
		session.setAttribute("addressObj",addr);
		   
		ModelAndView mv=new ModelAndView("ShowOrder");
		return mv;
   }
   

}
