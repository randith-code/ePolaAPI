package com.epola.ePolaAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter

public class Vehicle {

    private String vehicleModel;
    private String regNo;
    private int priceRate;
    private String capacity;
    private ArrayList<Review> reviews;
}
