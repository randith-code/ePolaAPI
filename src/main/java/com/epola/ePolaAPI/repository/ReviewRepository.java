package com.epola.ePolaAPI.repository;

import com.epola.ePolaAPI.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findReviewsByOwnerId(String ownerId);
    List<Review> findReviewsByOwnerIdAndItemId(String ownerId, String itemId);
}
