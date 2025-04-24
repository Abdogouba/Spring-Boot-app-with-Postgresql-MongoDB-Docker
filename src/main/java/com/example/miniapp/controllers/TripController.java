package com.example.miniapp.controllers;

import com.example.miniapp.models.Trip;
import com.example.miniapp.services.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping("/addTrip")
    public Trip addTrip(@RequestBody Trip trip) {
        return this.tripService.addTrip(trip);
    }

    @GetMapping("/allTrips")
    public List<Trip> getAllTrips() {
        return this.tripService.getAllTrips();
    }

    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable Long id) {
        Trip trip = this.tripService.getTripById(id);
        if (trip == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found");
        return trip;
    }

    @PutMapping("/update/{id}")
    public Trip updateTrip(@PathVariable Long id, @RequestBody Trip trip) {
        if (this.tripService.getTripById(id) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found");
        return this.tripService.updateTrip(id, trip);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTrip(@PathVariable Long id) {
        if (this.tripService.getTripById(id) == null)
            return "Trip not found";
        this.tripService.deleteTrip(id);
        return "Trip deleted successfully";
    }

    @GetMapping("/findByDateRange")
    public List<Trip> findTripsWithinDateRange(@RequestParam LocalDateTime startDate, @RequestParam
    LocalDateTime endDate) {
        return this.tripService.findTripsWithinDateRange(startDate, endDate);
    }

    @GetMapping("/findByCaptainId")
    public List<Trip> findTripsByCaptainId(@RequestParam Long captainId) {
        return this.tripService.findTripsByCaptainId(captainId);
    }

}
