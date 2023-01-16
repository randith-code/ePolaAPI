package com.epola.ePolaAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("trades")
public class Trade {
    @Id
    private String trdId;
    private String ownerId;
    private String bid;
    private int amount;
    private String date;
    private Boolean isOwnerAccepted;
    private Boolean active;
    private Boolean isBuyerAcceptedCompletion;
    private Boolean isSellerAcceptedCompletion;
}
