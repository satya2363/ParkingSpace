package com.parkingspace.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.parkingspace.models.Location;
import com.parkingspace.models.ParkingLot;
import com.parkingspace.models.ParkingSpot;
import com.parkingspace.repositories.ParkingRepository;
import com.parkingspace.services.QueryService;

@RestController
public class SpotsController {

    @Autowired
    ParkingRepository parkingRepository;

    @Autowired
    QueryService      queryService;

    @GetMapping(
            path = "/spotsByParkingLot")
    public @ResponseBody Iterable<ParkingSpot> getAvailableParkingSpots(@RequestParam int parkingLotId) {
        return queryService.spotQuery(parkingLotId);
    }
}
