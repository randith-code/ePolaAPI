package com.epola.ePolaAPI.model;

import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter

public class Review {

    private int rating;
    private Date postedDate;
    private String rName;
    private String review;
}
