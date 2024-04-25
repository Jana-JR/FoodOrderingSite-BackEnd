package com.jana.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    private IngredientCategory category;  // sauce ingr ---> tomato sau, soya sau, whitw sauce

    @JsonIgnore
    @ManyToOne  // one res have multiple ingr
    private Restaurant restaurant;

    private boolean inStock = true;

}
