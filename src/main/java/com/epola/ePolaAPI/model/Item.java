package com.epola.ePolaAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Document("item")
public class Item {
    @Id
    private String iid;
    private String iname;
    private String ownerID;
    private int priceRate;
    private Date postedDate = new Date(System.currentTimeMillis());
    private String description;
}
