package com.backend.daos;

import com.backend.models.Cart;



public interface CartDao {

	public boolean addCart(Cart cart);
	public Cart getCartByCustomer(String customerId);
    public boolean deleteCart(int cartId);

}
