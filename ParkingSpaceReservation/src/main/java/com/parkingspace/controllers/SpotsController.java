package com.parkingspace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.parkingspace.repositories.ParkingRepository;

@RestController
public class SpotsController {

    @Autowired
    ParkingRepository parkingRepository;

    //    @Autowired
    //    QueryService queryService;
    //
    //    @GetMapping(path = "/spotsByParkingLot")
    //    public @ResponseBody Iterable<ParkingSpot> getAvailableParkingSpots(@RequestParam int parkingLotId) {
    //        return queryService.spotQuery(parkingLotId);
    //    }

}
