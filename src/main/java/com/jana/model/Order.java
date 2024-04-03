package com.jana.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy  = GenerationType.AUTO)
    private Long id;

    @ManyToOne  // many orders have same user
    private User customer;

    @JsonIgnore  // whenever we fetch ORDER , we dont need restau obj
    @ManyToOne
    private  Restaurant restaurant;

    private Long totalAmount;

    private String orderStatus;

    private Date createdAt;


    @ManyToOne
    private Address deliveryAddress;

    @OneToMany
    private List<Orderitem> items;

    // private Payment payment;

    private int totalItem;
    private int totalPrice;







}
