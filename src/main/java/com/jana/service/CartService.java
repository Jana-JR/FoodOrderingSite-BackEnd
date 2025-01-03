package com.jana.service;

import com.jana.model.Cart;
import com.jana.model.CartItem;
import com.jana.request.AddCartItemRequest;

public interface CartService {
    public CartItem addItemToCart(AddCartItemRequest req, String jwt)throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity)throws Exception;
    public Cart removeItemFromCart(Long cartItemId, String jwt)throws Exception;

    public Long calculateCartTotals(Cart cart)throws Exception;

    public Cart findCartById(Long id)throws Exception;

    public Cart clearCart(Long userId)throws Exception;




}
