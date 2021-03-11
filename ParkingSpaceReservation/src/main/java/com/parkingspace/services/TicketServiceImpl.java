package com.parkingspace.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingspace.DTO.ParkingAvailabilityDTO;
import com.parkingspace.controllers.TicketController;
import com.parkingspace.models.ParkingTicket;
import com.parkingspace.repositories.FloorRepository;
import com.parkingspace.repositories.ParkingRepository;
import com.parkingspace.repositories.ParkingSpotRepository;
import com.parkingspace.repositories.TicketRepository;
import com.parkingspace.utils.RandomStringGenerator;

@Service
public class TicketServiceImpl implements ITicketService {
    Logger                       log             = LoggerFactory.getLogger(TicketController.class);
    int                          BAR_CODE_LENGTH = 10;
    @Autowired
    ParkingServiceImpl           parkingService;

    @Autowired
    public ParkingRepository     parkingRepo;

    @Autowired
    public TicketRepository      ticketRepo;

    @Autowired
    public ParkingSpotRepository parkingSpotRepo;

    @Autowired
    public FloorRepository       floorRepo;

    private String               IS_FREE         = "false";

    //multithreading / Thread pool
    @Override
    public ParkingTicket createTicket(ParkingTicket ticket) {
        //check if the parking is still available
        //by querying the parking lot table and get the total number of spots available.
        //if the current spot we book is the last one, then update availability in the table
        //ParkingAvailabilityDTO parkingDTO = queryService.getParkingLotAvailability(ticket.getParkingLotId(), ticket.getFloorNumber());
        ParkingAvailabilityDTO parkingDTO = parkingRepo.getparkingLotAvailability(ticket.getParkingLotId(), ticket.getFloorNumber());
        //async ?
        //exception handling
        if (parkingDTO.getIsFull().equals("false")) {
            log.info("Spots are available");
            parkingSpotRepo.updateSlot(IS_FREE, ticket.getLicenseNumber(), ticket.getSpotNumber(), parkingDTO.getFloorId());
            String spotsAvailable = parkingDTO.getTotalSpots() < 2 ? "false" : "true";
            floorRepo.updateFloor(spotsAvailable, ticket.getFloorNumber(), parkingDTO.getTotalSpots() - 1, ticket.getParkingLotId());
            ticket.setBarCode(getbarCode());
            ticket.setEndTime(findEndTime(ticket.getStartTime()));
            ticketRepo.save(ticket);
        } else {
            log.error("No more Spots Available");
            //Throw exception
        }
        return ticket;
    }

    public Optional<ParkingTicket> getTicketById(int ticketId) {
        return ticketRepo.findById(ticketId);
    }

    private LocalDateTime findEndTime(LocalDateTime startTime) {
        return startTime.plusHours(2);
    }

    private String getbarCode() {
        RandomStringGenerator randString = new RandomStringGenerator();
        return randString.getAlphaNumericCode(BAR_CODE_LENGTH);
    }
}
