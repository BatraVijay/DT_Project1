package com.frontend.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.backend.daos.CartDao;
import com.backend.daos.ItemDao;
import com.backend.daos.OrderDao;
import com.backend.models.Cart;
import com.backend.models.Item;
import com.backend.models.Order;


@Controller
public class OrderController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	ItemDao itemDao;
	@Autowired
	OrderDao orderDao;
	@Autowired
	CartDao cartDao;
	@Autowired
	HttpSession session;
	
	@RequestMapping(value="/getOrderPage",method=RequestMethod.GET)
	public ModelAndView getOrderPage(){
		ModelAndView mv=new ModelAndView("Payment");
		double price=(Double)session.getAttribute("total");
		
		Order order=new Order();
		order.setTotalPrice(price+100);
		mv.addObject("orderObj",order);
        return mv;
	}


		@RequestMapping(value="addToCart/processOrder",method=RequestMethod.POST)
		public ModelAndView processOrder(@ModelAttribute("orderObj")Order orderObj){
			String email=request.getUserPrincipal().getName();
		
			Cart cartObj=cartDao.getCartByCustomer(email);
			List<Item> items=itemDao.getItemsListByCart(cartObj.getCartId());
			double sum=0;
			for(Item item:items){
				sum=sum+item.getPrice();
			}
			
			orderDao.makeOrder(orderObj);
			cartDao.deleteCart(cartObj.getCartId());
			
			ModelAndView mv=new ModelAndView("ThankYou");
			mv.addObject("totalAmountToPay",sum);
			return mv;
		}
	

}
