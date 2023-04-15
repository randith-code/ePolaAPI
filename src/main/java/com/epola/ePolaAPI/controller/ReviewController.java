package com.epola.ePolaAPI.controller;

import com.epola.ePolaAPI.model.Item;
import com.epola.ePolaAPI.model.Review;
import com.epola.ePolaAPI.repository.ReviewRepository;
import com.epola.ePolaAPI.resource.ReviewRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ePolaAPI")
public class ReviewController {

    private final ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/review")
    public ResponseEntity<List<Review>> getAllReviews(){
        return ResponseEntity.ok(this.reviewRepository.findAll());
    }

    @PostMapping("/review")
    public ResponseEntity<Review> addReview(@RequestBody ReviewRequest reviewRequest){
        Review review = new Review();

        review.setOwnerId(reviewRequest.getOwnerId());
        review.setItemId(reviewRequest.getItemId());
        review.setPostedDate(new Date(System.currentTimeMillis()));
        review.setRName(reviewRequest.getRName());
        review.setRating(reviewRequest.getRating());
        review.setReview(reviewRequest.getReview());

        return ResponseEntity.status(201).body(this.reviewRepository.save(review));

    }

    @GetMapping("/review/{oid}")
    public ResponseEntity<List<Review>> getByOwnerId(@PathVariable String oid){

        Optional<List<Review>> review = Optional.ofNullable(this.reviewRepository.findReviewsByOwnerId(oid));

        if (review.isPresent()){
            return ResponseEntity.ok(review.get());
        }
        else {
            return null;
        }
    }

    @GetMapping("/review/{oid}/{iid}")
    public ResponseEntity<List<Review>> getByItemId(@PathVariable("oid") String oid, @PathVariable("iid") String iid){

        Optional<List<Review>> review = Optional.ofNullable(this.reviewRepository.findReviewsByOwnerIdAndItemId(oid, iid));

        if (review.isPresent()){
            return ResponseEntity.ok(review.get());
        }
        else {
            return null;
        }
    }

    @DeleteMapping("/review/{rid}")
    public ResponseEntity deleteReview(@PathVariable String rid){
        Optional<Review> review = reviewRepository.findById(rid);

        if (review.isPresent()){
            this.reviewRepository.deleteById(rid);
            return ResponseEntity.ok("Success");
        }
        else {
            return ResponseEntity.ok("Review with id " + rid + " is not found");
        }
    }
}
