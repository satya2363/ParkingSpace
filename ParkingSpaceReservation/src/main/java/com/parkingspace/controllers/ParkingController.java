package com.parkingspace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.parkingspace.models.ParkingLot;
import com.parkingspace.repositories.ParkingRepository;
import com.parkingspace.services.ParkingServiceImpl;

@RestController
public class ParkingController {

    @Autowired
    ParkingRepository  parkingRepository;

    @Autowired
    ParkingServiceImpl parkingService;

    @RequestMapping(
            value = "/greeting")
    public String getHelloWorld() {
        String message = "Hello World !";
        return message;
    }

    @GetMapping(
            path = "/parking/all")
    public @ResponseBody Iterable<ParkingLot> getAllParkingLots() {
        return parkingRepository.findAll();
    }

    @GetMapping(
            path = "/parking/lotsByCityAndZipcode")
    public @ResponseBody Iterable<ParkingLot> getAllParkingLots(@RequestParam String city, @RequestParam int zipcode) {
        //verify params
        //Authentication
        //Throttle
        return parkingService.parkingLotQuery(city, zipcode);
    }
}
