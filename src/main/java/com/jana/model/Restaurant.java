package com.jana.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User owner;

    private String name;
    private String description;
    private String cuisineType;

    @OneToOne
    private Address address;

    @Embedded
    private ContactInformation contactInformation;

    private String openingHours;

    //reviews
    // private

    @OneToMany(mappedBy= "restaurant", cascade = CascadeType.ALL)
    private List<Order> orders= new ArrayList<>();

    // private numRating

    @ElementCollection
    @Column(length = 1000)  // default 255
    private List<String> images;

    private LocalDateTime registrationDate;

    private boolean open;  // when close user can't order

    @JsonIgnore // whn we ftch foods we dont need food list.... instead we 'll create seperate api for fetch food
    @OneToMany(mappedBy = "foods", cascade = CascadeType.ALL)  // one res can haav multiple foods
    private List<Food> foods = new ArrayList<>();
}
