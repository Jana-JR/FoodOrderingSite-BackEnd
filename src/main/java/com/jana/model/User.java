package com.jana.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jana.dto.RestaurantDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity  // Map our java class with DB table
@Data  // for getter setter mtds
@AllArgsConstructor
@NoArgsConstructor

public class User {

    @Id // we're telling this id is unique identifier for this entity
    @GeneratedValue(strategy = GenerationType.AUTO)  // Generate id automatically
    private Long id;


    private String fullName;


    private String email;


    private String password;

    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;

    @JsonIgnore
    // used at field level to mark a property or list of properties to be ignored.
    // whenever fetching USER entity we dont need this order list
    // to fetching ordrs we'll write seperate api

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    // by mappedBy --> telling springboot, dont create seperate table for his maapping, U can use order table
    // one user have many relation to Order class
    private List<Order> orders = new ArrayList<>();

    @ElementCollection
    private List<RestaurantDto> favourites = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //CascadeType.ALL --> if CRUD perf in a parent entity, all child entities associated with it will also be persisted, updated, or deleted
    // orphanRemoval = true: "Child" entity is removed when it's no longer referenced (its parent may not be removed).

    private List<Address> addresses = new ArrayList<>();


}
