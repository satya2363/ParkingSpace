package com.parkingspace.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.parkingspace.DTO.ParkingAvailabilityDTO;
import com.parkingspace.models.ParkingTicket;
import com.parkingspace.repositories.ParkingRepository;
import com.parkingspace.services.QueryService;

@RestController
public class TicketController {
    Logger                   log = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    QueryService             queryService;

    @Autowired
    public ParkingRepository parkingRepo;

    @PostMapping(
            path = "/generateTicket")
    public @ResponseBody boolean generateTicket(@RequestBody ParkingTicket ticket) {
        //check if the parking is still available
        //by querying the parking lot table and get the total number of spots available.
        //if the current spot we book is the last one, then update availability in the table
        //ParkingAvailabilityDTO parkingDTO = queryService.getParkingLotAvailability(ticket.getParkingLotId(), ticket.getFloorNumber());
        ParkingAvailabilityDTO parkingDTO = parkingRepo.parkingLotAvailable(ticket.getParkingLotId(), ticket.getFloorNumber());

        if (parkingDTO.getIsFull().equals("false")) {
            log.info("Pots are available");
            //if total spots<2 total spots =0, is available=false 
        } else {

        }

        return false;
        //return queryService.parkingLotQuery(city, zipcode);
    }

    @GetMapping(
            path = "/getTicket")
    public @ResponseBody ParkingTicket getTicket(@RequestParam int ticketNumber) {

        return null;
        //return queryService.parkingLotQuery(city, zipcode);
    }
}
