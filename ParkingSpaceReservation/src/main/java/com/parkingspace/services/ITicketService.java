package com.parkingspace.services;

import com.parkingspace.Exceptions.NoResultForQueryException;
import com.parkingspace.models.ParkingTicket;

public interface ITicketService {
    ParkingTicket createTicket(ParkingTicket ticketInfo) throws NoResultForQueryException;

}
