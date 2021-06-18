package com.parkingspace.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.parkingspace.DTO.ParkingAvailabilityDTO;
import com.parkingspace.models.ParkingLot;
import com.parkingspace.models.ParkingSpot;

public interface IParkingService {

    CompletableFuture<List<ParkingLot>> parkingLotQuery(String city, int zipcode);

    ParkingAvailabilityDTO getParkingLotAvailability(int parkingId, int floorNumber);

    List<ParkingSpot> spotQuery(int parkingLotId);

}
