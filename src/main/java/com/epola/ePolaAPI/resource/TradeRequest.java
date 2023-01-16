package com.epola.ePolaAPI.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TradeRequest {
    private String ownerId;
    private String bid;
    private int amount;
    private String date;
    private Boolean isOwnerAccepted;
    private Boolean active;
    private Boolean isBuyerAcceptedCompletion;
    private Boolean isSellerAcceptedCompletion;
}
