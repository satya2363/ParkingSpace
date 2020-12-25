package com.parkingspace.repositories;

import org.springframework.data.repository.CrudRepository;

import com.parkingspace.models.ParkingSpot;

public interface SpotRepository extends CrudRepository<ParkingSpot, Integer> {
}
