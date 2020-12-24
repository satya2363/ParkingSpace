package com.parkingspace.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.parkingspace.models.ParkingTicket;

@RestController
public class TicketController {

    @PostMapping(
            path = "/generateTicket")
    public @ResponseBody boolean generateTicket(@RequestParam ParkingTicket ticket) {

        return false;
        //return queryService.parkingLotQuery(city, zipcode);
    }

    @GetMapping(
            path = "/getTicket")
    public @ResponseBody ParkingTicket getTicket(@RequestParam String city) {

        return null;
        //return queryService.parkingLotQuery(city, zipcode);
    }
}
