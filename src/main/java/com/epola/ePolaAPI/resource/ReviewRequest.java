package com.epola.ePolaAPI.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class ReviewRequest {
    
    private int rating;
    private Date postedDate;
    private String rName;
    private String review;
}
