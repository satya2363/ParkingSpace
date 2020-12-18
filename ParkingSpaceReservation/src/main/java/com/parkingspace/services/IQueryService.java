package com.parkingspace.services;

import java.util.List;

import com.parkingspace.models.ParkingLot;

public interface IQueryService {

    List<ParkingLot> parkingLotQuery(String city, int zipcode);
}
