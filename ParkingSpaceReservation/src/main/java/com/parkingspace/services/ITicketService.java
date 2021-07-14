package com.parkingspace.services;

import java.util.List;

import com.parkingspace.DTO.ExpiredTicketDTO;
import com.parkingspace.Exceptions.NoResultForQueryException;
import com.parkingspace.models.ParkingTicket;

public interface ITicketService {
    ParkingTicket createTicket(ParkingTicket ticketInfo) throws NoResultForQueryException;

    List<ExpiredTicketDTO> getExpiredParkingTickets();
}
