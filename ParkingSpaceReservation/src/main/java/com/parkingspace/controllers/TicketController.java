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

import com.parkingspace.models.ParkingTicket;
import com.parkingspace.repositories.FloorRepository;
import com.parkingspace.repositories.ParkingRepository;
import com.parkingspace.repositories.ParkingSpotRepository;
import com.parkingspace.repositories.TicketRepository;
import com.parkingspace.services.ParkingServiceImpl;
import com.parkingspace.services.TicketServiceImpl;

@RestController
public class TicketController {
    Logger                       log             = LoggerFactory.getLogger(TicketController.class);
    int                          BAR_CODE_LENGTH = 10;
    @Autowired
    ParkingServiceImpl           parkingService;
    @Autowired
    TicketServiceImpl            ticketService;
    @Autowired
    public ParkingRepository     parkingRepo;

    @Autowired
    public TicketRepository      ticketRepo;

    @Autowired
    public ParkingSpotRepository parkingSpotRepo;

    @Autowired
    public FloorRepository       floorRepo;

    private String               IS_FREE         = "false";

    // multi threaded testing
    @PostMapping(
            path = "/Ticket/generateTicket")
    public @ResponseBody ParkingTicket generateTicket(@RequestBody ParkingTicket ticket) {
        return ticketService.createTicket(ticket);
    }

    @GetMapping(
            path = "/Ticket/getTicket")
    public @ResponseBody Optional<ParkingTicket> getTicket(@RequestParam int ticketId) {
        return ticketService.getTicketById(ticketId);
    }

}
