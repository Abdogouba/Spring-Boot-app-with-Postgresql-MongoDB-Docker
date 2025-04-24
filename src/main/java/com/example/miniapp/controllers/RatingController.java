package com.example.miniapp.controllers;

import com.example.miniapp.models.Rating;
import com.example.miniapp.services.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/addRating")
    public Rating addRating(@RequestBody Rating rating) {
        return ratingService.addRating(rating);
    }

    @PutMapping("/update/{id}")
    public Rating updateRating(@PathVariable String id, @RequestBody Rating updatedRating) {
        if (this.ratingService.getRatingById(id) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rating not found");
        return this.ratingService.updateRating(id, updatedRating);
    }

    @GetMapping("/{id}")
    public Rating findRatingById(@PathVariable String id) {
        Rating rating = this.ratingService.getRatingById(id);
        if (rating == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rating not found");
        return rating;
    }

    @GetMapping("/allRatings")
    public List<Rating> findAllRatings() {
        return this.ratingService.getAllRatings();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRating(@PathVariable String id) {
        if (this.ratingService.getRatingById(id) == null)
            return "Rating not found";
        this.ratingService.deleteRating(id);
        return "Rating deleted successfully";
    }

    @GetMapping("/findByEntity")
    public List<Rating> findRatingsByEntity(@RequestParam Long entityId, @RequestParam String
            entityType) {
        return this.ratingService.getRatingsByEntity(entityId, entityType);
    }

    @GetMapping("/findAboveScore")
    public List<Rating> findRatingsAboveScore(@RequestParam int minScore) {
        return this.ratingService.findRatingsAboveScore(minScore);
    }


}
