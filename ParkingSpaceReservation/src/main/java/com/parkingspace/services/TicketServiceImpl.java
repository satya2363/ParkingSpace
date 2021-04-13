package com.parkingspace.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingspace.DTO.SpotAvailabilityDTO;
import com.parkingspace.Exceptions.NoResultForQueryException;
import com.parkingspace.controllers.TicketController;
import com.parkingspace.models.ParkingTicket;
import com.parkingspace.repositories.FloorRepository;
import com.parkingspace.repositories.ParkingRepository;
import com.parkingspace.repositories.ParkingSpotRepository;
import com.parkingspace.repositories.SpotRepository;
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
    public SpotRepository        spotRepo;
    @Autowired
    public FloorRepository       floorRepo;

    private String               IS_NOT_FREE     = "false";

    //multithreading / Thread pool
    @Override
    public ParkingTicket createTicket(ParkingTicket ticket) throws NoResultForQueryException {

        //Fetch spot types and their counts
        Set<SpotAvailabilityDTO> spotTypeCountDTOResults = spotRepo.getparkingSpotAvailability(ticket.getParkingLotId(), ticket.getSpotType());
        SpotAvailabilityDTO spotTypeCountDTO = spotTypeCountDTOResults.stream().findFirst().orElse(new SpotAvailabilityDTO(-1, -1, -1, "", "", -1, -1)); //handle this with custom exceptions
        //async ?
        //TODO exception handling
        System.out.println(spotTypeCountDTO.getFloorId() + " == " + spotTypeCountDTO.getParkingLotId() + "== " + spotTypeCountDTO.getSpotType() + " == " + spotTypeCountDTO.getSpotTypeCount());
        if (spotTypeCountDTO.getTotalSpots() > 0) {
            log.info("Spots are available");
            //ParkingAvailabilityDTO parkingDTO = parkingService.getParkingLotAvailability(ticket.getParkingLotId(), ticket.getFloorNumber());
            parkingSpotRepo.updateSlot(IS_NOT_FREE, ticket.getLicenseNumber(), spotTypeCountDTO.getSpotNumber(), spotTypeCountDTO.getFloorId());
            String spotsAvailable = spotTypeCountDTO.getTotalSpots() < 2 ? "false" : "true";
            //TODO make this async
            floorRepo.updateFloor(spotsAvailable, ticket.getFloorNumber(), spotTypeCountDTO.getTotalSpots() - 1, ticket.getParkingLotId());
            ticket.setBarCode(getbarCode());
            ticket.setEndTime(findEndTime(ticket.getStartTime(), ticket.getDuration()));
            ticket.setFloorNumber(spotTypeCountDTO.getFloorId());
            ticket.setSpotNumber(spotTypeCountDTO.getSpotNumber());
            //ticket.set
            ticketRepo.save(ticket);
        } else if (spotTypeCountDTO.getTotalSpots() < 0) {
            log.error("No result for the query");
            throw new NoResultForQueryException("No result for the query");
            // TODO Throw exception
        } else {
            log.error("No more Spots Available");
            //TODO Throw exception
        }
        return ticket;
    }

    public Optional<ParkingTicket> getTicketById(int ticketId) {
        return ticketRepo.findById(ticketId);
    }

    private LocalDateTime findEndTime(LocalDateTime startTime, long duration) {
        return startTime.plusHours(duration);
    }

    private String getbarCode() {
        RandomStringGenerator randString = new RandomStringGenerator();
        return randString.getAlphaNumericCode(BAR_CODE_LENGTH);
    }
}
