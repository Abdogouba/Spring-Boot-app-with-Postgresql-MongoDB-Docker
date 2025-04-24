package com.example.miniapp.controllers;

import com.example.miniapp.models.Captain;
import com.example.miniapp.services.CaptainService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/captain")
public class CaptainController {

    private final CaptainService captainService;

    public CaptainController(CaptainService captainService) {
        this.captainService = captainService;
    }

    @PostMapping("/addCaptain")
    public Captain addCaptain(@RequestBody Captain captain) {
        return this.captainService.addCaptain(captain);
    }

    @GetMapping("/allCaptains")
    public List<Captain> getAllCaptains() {
        return this.captainService.getAllCaptains();
    }

    @GetMapping("/{id}")
    public Captain getCaptainById(@PathVariable Long id) {
        Captain captain = this.captainService.getCaptainById(id);
        if (captain == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Captain not found");
        return captain;
    }

    @GetMapping("/filterByRating")
    public List<Captain> getCaptainsByRating(@RequestParam Double ratingThreshold) {
        return this.captainService.getCaptainsByRating(ratingThreshold);
    }

    @GetMapping("/filterByLicenseNumber")
    public Captain getCaptainByLicenseNumber(@RequestParam String licenseNumber) {
        Captain captain = this.captainService.getCaptainByLicenseNumber(licenseNumber);
//        if (captain == null)
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Captain not found");
        return captain;
    }

}
