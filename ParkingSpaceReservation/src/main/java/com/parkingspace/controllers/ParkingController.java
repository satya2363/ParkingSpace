package com.parkingspace.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.parkingspace.models.ParkingLot;
import com.parkingspace.repositories.ParkingRepository;
import com.parkingspace.services.IParkingService;

@RestController
public class ParkingController {

    @Autowired
    ParkingRepository       parkingRepository;

    @Autowired
    private IParkingService parkingService;

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
    public @ResponseBody CompletableFuture<List<ParkingLot>> getAllParkingLots(@RequestParam String city, @RequestParam int zipcode) {
        //verify params
        //Authentication
        //Throttle
        return parkingService.parkingLotQuery(city, zipcode);
    }

    @GetMapping(
            path = "/getMultipleRequests")
    public ResponseEntity getMultipleRequests() {
        CompletableFuture<List<ParkingLot>> parkingLot1 = parkingService.parkingLotQuery("Boston", 2144);
        CompletableFuture<List<ParkingLot>> parkingLot2 = parkingService.parkingLotQuery("Boston", 2203);
        CompletableFuture<List<ParkingLot>> parkingLot3 = parkingService.parkingLotQuery("Waltham", 2453);
        CompletableFuture<List<ParkingLot>> parkingLot4 = parkingService.parkingLotQuery("Woburn", 1813);
        CompletableFuture<List<ParkingLot>> parkingLot5 = parkingService.parkingLotQuery("Springfield", 1152);
        CompletableFuture.allOf(parkingLot1, parkingLot2, parkingLot3, parkingLot4, parkingLot5).join();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
