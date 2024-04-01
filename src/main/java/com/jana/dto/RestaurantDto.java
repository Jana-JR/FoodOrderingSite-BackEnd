package com.jana.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class RestaurantDto {

    private String title;

    @Column(length = 1000) // for image url we need long space
    private List<String> images;

    private String description;

    private Long id;
}
