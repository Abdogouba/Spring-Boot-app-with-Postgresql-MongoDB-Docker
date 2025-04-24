package com.example.miniapp.repositories;

import com.example.miniapp.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip,Long> {

    List<Trip> findByCaptain_Id(Long captainId);

    List<Trip> findByTripDateBetween(LocalDateTime tripDateAfter, LocalDateTime tripDateBefore);

}
