package com.epola.ePolaAPI.model;

import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Item {

    private String iname;
    private int priceRate;
    private Date postedDate;
    private String description;
    private ArrayList<Review> reviews;

    public void setReviews(Review review){
        this.reviews.add(review);
    }

    public void setReviewList(ArrayList<Review> r){
        this.reviews = r;
    }
}
