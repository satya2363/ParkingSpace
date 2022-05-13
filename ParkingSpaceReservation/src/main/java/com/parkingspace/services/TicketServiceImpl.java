package com.parkingspace.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingspace.DTO.ExpiredTicketDTO;
import com.parkingspace.DTO.SpotAvailabilityDTO;
import com.parkingspace.Exceptions.NoResultForQueryException;
import com.parkingspace.models.ParkingTicket;
import com.parkingspace.models.RegisteredUser;
import com.parkingspace.repositories.FloorRepository;
import com.parkingspace.repositories.ParkingRepository;
import com.parkingspace.repositories.ParkingSpotRepository;
import com.parkingspace.repositories.TicketRepository;
import com.parkingspace.repositories.UserRepository;
import com.parkingspace.utils.RandomStringGenerator;

@Service
public class TicketServiceImpl implements ITicketService {
    Logger                       log             = LoggerFactory.getLogger(TicketServiceImpl.class);
    int                          BAR_CODE_LENGTH = 10;
    @Autowired
    private IParkingService      parkingService;
    @Autowired
    public ParkingRepository     parkingRepo;
    @Autowired
    public TicketRepository      ticketRepo;
    @Autowired
    public ParkingSpotRepository parkingSpotRepo;

    @Autowired
    public FloorRepository       floorRepo;
    @Autowired
    public UserRepository        userRepo;

    private String               IS_NOT_FREE     = "false";

    //multithreading / Thread pool
    @Override
    public ParkingTicket createTicket(ParkingTicket ticket) throws NoResultForQueryException {

        //Fetch spot types and their counts
        Set<SpotAvailabilityDTO> spotTypeCountDTOResults = parkingSpotRepo.getparkingSpotAvailability(ticket.getParkingLotId(), ticket.getSpotType());
        SpotAvailabilityDTO spotTypeCountDTO = spotTypeCountDTOResults.stream().findFirst().orElse(new SpotAvailabilityDTO(-1, -1, -1, -1, "", "", "", -1)); //handle this with custom exceptions
        //async ?
        //TODO exception handling

        if (spotTypeCountDTO.getTotalSpots() > 0) {
            log.debug("Spots are available");
            parkingSpotRepo.updateSlot(IS_NOT_FREE, ticket.getLicenseNumber(), spotTypeCountDTO.getSpotNumber(), spotTypeCountDTO.getFloorId());
            String spotsAvailable = spotTypeCountDTO.getTotalSpots() < 2 ? "false" : "true";
            //TODO make this async
            floorRepo.updateFloor(spotsAvailable, ticket.getFloorNumber(), spotTypeCountDTO.getTotalSpots() - 1, ticket.getParkingLotId());
            userRepo.updateUser(ticket.getStatus(), ticket.getPhoneNumber());
            //ticket.set
            ticket = setTicket(ticket, spotTypeCountDTO);

            Optional<RegisteredUser> optionalUser = userRepo.findById(ticket.getPhoneNumber());

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

    private ParkingTicket setTicket(ParkingTicket ticket, SpotAvailabilityDTO spotTypeCountDTO) {
        ticket.setBarCode(getbarCode());
        ticket.setEndTime(findEndTime(ticket.getStartTime(), ticket.getDuration()));
        ticket.setFloorNumber(spotTypeCountDTO.getFloorNumber());
        ticket.setSpotNumber(spotTypeCountDTO.getSpotNumber());
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

    @Override
    public List<ExpiredTicketDTO> getExpiredParkingTickets() {
        System.out.println("Expired car spots");
        return ticketRepo.getExpiredParkingTickets();

    }
}
