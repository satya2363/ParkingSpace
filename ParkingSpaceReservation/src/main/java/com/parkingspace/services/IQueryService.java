package com.parkingspace.services;

import java.util.List;

import com.parkingspace.DTO.ParkingAvailabilityDTO;
import com.parkingspace.models.ParkingLot;

public interface IQueryService {

    List<ParkingLot> parkingLotQuery(String city, int zipcode);

    ParkingAvailabilityDTO getParkingLotAvailability(int parkingId, int floorNumber);
}
