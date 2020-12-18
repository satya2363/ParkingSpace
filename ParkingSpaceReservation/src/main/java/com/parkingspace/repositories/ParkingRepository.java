package com.parkingspace.repositories;

import org.springframework.data.repository.CrudRepository;

import com.parkingspace.models.ParkingLot;

public interface ParkingRepository extends CrudRepository<ParkingLot, Integer> {
}
