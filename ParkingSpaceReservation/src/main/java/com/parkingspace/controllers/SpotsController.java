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
import com.parkingspace.repositories.ParkingRepository;
import com.parkingspace.services.QueryService;

@RestController
public class SpotsController {

    @Autowired
    ParkingRepository parkingRepository;

    @Autowired
    QueryService      queryService;

    @RequestMapping(
            value = "/greeting")
    public String getHelloWorld() {
        String message = "Hello World !";
        return message;
    }

    // by parking location | parking lot id
    @GetMapping(
            path = "/spotsByParkingLot")
    public @ResponseBody Iterable<ParkingSpot> getAllParkingSpots(@RequestParam int parkingLotId) {
        return queryService.spotQuery(parkingLotId);
    }
}
