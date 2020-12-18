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
public class ParkingController {

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

    @GetMapping(
            path = "/all")
    public @ResponseBody Iterable<ParkingLot> getAllParkingLots() {
        List<ParkingLot> parkingLots = (List<ParkingLot>) parkingRepository.findAll();
        parkingLots.stream().map(p -> p.getAddress().getStreet_name()).forEach(System.out::println);
        ;
        //System.out.println(parkingRepository.findAll());
        // This returns a JSON or XML with the users
        return parkingRepository.findAll();
    }

    @GetMapping(
            path = "/LotsByCityAndZipcode")
    public @ResponseBody Iterable<ParkingLot> getAllParkingLots(@RequestParam String city, @RequestParam int zipcode) {
        Location address = new Location();
        address.setCity(city);
        address.setZipcode(zipcode);
        return queryService.parkingLotQuery(city, zipcode);

    }
}
