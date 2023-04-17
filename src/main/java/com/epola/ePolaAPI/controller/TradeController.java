package com.epola.ePolaAPI.controller;

import com.epola.ePolaAPI.exceptions.ResourceNotFoundException;
import com.epola.ePolaAPI.model.Trade;
import com.epola.ePolaAPI.repository.TradeRepository;
import com.epola.ePolaAPI.resource.TradeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ePolaAPI")
@CrossOrigin("http://localhost:3000")
public class TradeController {

    private final TradeRepository tradeRepository;

    public TradeController(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @GetMapping("/trade/{bid}")
    public ResponseEntity<List<Trade>> getTradesByBuyerId(@PathVariable String bid){
        return ResponseEntity.ok(this.tradeRepository.findTradesByBid(bid));
    }
    @GetMapping("/trade/{ownerId}")
    public ResponseEntity<List<Trade>> getTradesByOwnerId(@PathVariable String ownerId){
        return ResponseEntity.ok(this.tradeRepository.findByOwnerId(ownerId));
    }
    @PostMapping("/trade")
    public ResponseEntity<Trade> addTrade(@RequestBody TradeRequest tradeRequest){
        Trade trade = new Trade();

        trade.setBid(tradeRequest.getBid());
        trade.setOwnerId(tradeRequest.getOwnerId());
        trade.setAmount(tradeRequest.getAmount());
        trade.setDate(tradeRequest.getDate());
        trade.setIsOwnerAccepted(tradeRequest.getIsOwnerAccepted());
        trade.setActive(tradeRequest.getActive());
        trade.setIsBuyerAcceptedCompletion(tradeRequest.getIsBuyerAcceptedCompletion());
        trade.setIsSellerAcceptedCompletion(tradeRequest.getIsSellerAcceptedCompletion());

        return ResponseEntity.status(201).body(this.tradeRepository.save(trade));
    }
    @PatchMapping("/trade/{trdId}")
    public ResponseEntity updateTradeStatus(@PathVariable String trdId, @RequestBody TradeRequest tradeRequest){
        Trade trade = this.tradeRepository.findById(trdId).
                orElseThrow(() -> new ResourceNotFoundException("Trade not exist with id: " + trdId));
        if(tradeRequest.getIsOwnerAccepted() != null){
            trade.setIsOwnerAccepted(tradeRequest.getIsOwnerAccepted());
        }
        if (tradeRequest.getActive() != null){
            trade.setActive(tradeRequest.getActive());
        }
        if (tradeRequest.getIsSellerAcceptedCompletion() != null){
            trade.setIsSellerAcceptedCompletion(tradeRequest.getIsSellerAcceptedCompletion());
        }
        if (tradeRequest.getIsBuyerAcceptedCompletion() != null){
            trade.setIsBuyerAcceptedCompletion(tradeRequest.getIsBuyerAcceptedCompletion());
        }

        return ResponseEntity.status(201).body(this.tradeRepository.save(trade));
    }

    @DeleteMapping("/trade/{trdId}")
    public ResponseEntity deleteTrade(@PathVariable String trdId){
        Optional<Trade> trade = this.tradeRepository.findById(trdId);

        if(trade.isPresent()){
            this.tradeRepository.deleteById(trdId);
            return ResponseEntity.ok("Success");
        }
        else {
            return ResponseEntity.ok("Trade with id " + trdId + " is not found");
        }
    }
}
