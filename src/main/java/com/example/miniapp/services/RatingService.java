package com.example.miniapp.services;

import com.example.miniapp.models.Rating;
import com.example.miniapp.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating addRating(Rating rating) {
        if (rating != null)
            rating.setId(null);
        return ratingRepository.save(rating);
    }

    public Rating updateRating(String id, Rating updatedRating) {
        Rating rating = this.ratingRepository.findById(id).get();
        if (updatedRating.getComment() != null)
            rating.setComment(updatedRating.getComment());
        if (updatedRating.getScore() != null)
            rating.setScore(updatedRating.getScore());
        return ratingRepository.save(rating);
    }

    public void deleteRating(String id) {
        ratingRepository.deleteById(id);
    }

    public List<Rating> getRatingsByEntity(Long entityId, String entityType) {
        return ratingRepository.findByEntityIdAndEntityType(entityId, entityType);
    }

    public List<Rating> findRatingsAboveScore(int minScore) {
        return this.ratingRepository.findByScoreGreaterThan(minScore);
    }

    public Rating getRatingById(String id) {
        return this.ratingRepository.findById(id).orElse(null);
    }

    public List<Rating> getAllRatings() {
        return this.ratingRepository.findAll();
    }
}
