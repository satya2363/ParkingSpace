package com.parkingspace.controllers;

import java.util.Optional;

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
import com.parkingspace.repositories.FloorRepository;
import com.parkingspace.repositories.ParkingRepository;
import com.parkingspace.repositories.ParkingSpotRepository;
import com.parkingspace.repositories.TicketRepository;
import com.parkingspace.services.QueryService;

@RestController
public class TicketController {
    Logger                       log     = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    QueryService                 queryService;

    @Autowired
    public ParkingRepository     parkingRepo;

    @Autowired
    public TicketRepository      ticketRepo;

    @Autowired
    public ParkingSpotRepository parkingSpotRepo;

    @Autowired
    public FloorRepository       floorRepo;

    private String               IS_FREE = "false";

    @PostMapping(
            path = "/generateTicket")
    public @ResponseBody boolean generateTicket(@RequestBody ParkingTicket ticket) {
        //check if the parking is still available
        //by querying the parking lot table and get the total number of spots available.
        //if the current spot we book is the last one, then update availability in the table
        //ParkingAvailabilityDTO parkingDTO = queryService.getParkingLotAvailability(ticket.getParkingLotId(), ticket.getFloorNumber());
        ParkingAvailabilityDTO parkingDTO = parkingRepo.parkingLotAvailable(ticket.getParkingLotId(), ticket.getFloorNumber());

        if (parkingDTO.getIsFull().equals("false")) {
            log.info("Spots are available");
            parkingSpotRepo.updateSlot(IS_FREE, ticket.getLicenseNumber(), ticket.getSpotNumber(), parkingDTO.getFloorId());
            String spotsAvailable = parkingDTO.getTotalSpots() < 2 ? "false" : "true";
            floorRepo.updateFloor(spotsAvailable, ticket.getFloorNumber(), parkingDTO.getTotalSpots() - 1, ticket.getParkingLotId());
            ParkingTicket pt = ticketRepo.save(ticket);
            return pt != null ? true : false;
        } else {
            log.error("No more Spots Available");
            //Throw exception
            return false;
        }

        //return queryService.parkingLotQuery(city, zipcode);
    }

    @GetMapping(
            path = "/getTicket")
    public @ResponseBody Optional<ParkingTicket> getTicket(@RequestParam int ticketId) {
        return ticketRepo.findById(ticketId);
    }
}
