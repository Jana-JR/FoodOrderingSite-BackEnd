package com.jana.service;

import com.jana.dto.RestaurantDto;
import com.jana.model.Restaurant;
import com.jana.model.User;
import com.jana.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

    public  Restaurant updateRestaurant (Long restaurantId, CreateRestaurantRequest updatedRestaurant); // create and update have same fields So dont need seperate request

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant> searchRestaurant();

    public Restaurant findRestaurantById(Long id) throws Exception;

    public Restaurant getRestaurantByUserId(Long id) throws Exception;

    public RestaurantDto addToFavourites(Long restaurantId, User user)throws Exception;

    public Restaurant updateRestaurantStatus(Long id)throws Exception;






}
