package com.jana.controller;

import com.jana.model.Food;
import com.jana.model.User;
import com.jana.service.FoodService;
import com.jana.service.RestaurantService;
import com.jana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                           @RequestHeader("Authorization") String jwt) throws Exception{

        User user= userService.findUserByJwtToken(jwt);

        List<Food> foods= foodService.searchFood(name);
        return new ResponseEntity<>(foods, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @RequestParam boolean vegetarian,
            @RequestParam boolean seasonal,
            @RequestParam boolean nonveg,
            @PathVariable Long restaurantId,
            @RequestParam(required = false) String food_category,
            @RequestHeader("Authorization") String jwt) throws Exception{

        User user= userService.findUserByJwtToken(jwt);

        List<Food> foods= foodService.getRestaurantFood(restaurantId,vegetarian,seasonal,nonveg,food_category);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
