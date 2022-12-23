package com.epola.ePolaAPI.resource;

import com.epola.ePolaAPI.model.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class ItemRequest {
    private String iname;
    private int priceRate;
    private String description;
}
