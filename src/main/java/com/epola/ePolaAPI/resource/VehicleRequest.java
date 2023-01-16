package com.epola.ePolaAPI.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VehicleRequest {

    private String ownerId;
    private String vehicleModel;
    private String regNo;
    private int priceRate;
    private String capacity;
}
