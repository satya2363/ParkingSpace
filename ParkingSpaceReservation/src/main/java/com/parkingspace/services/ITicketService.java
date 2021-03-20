package com.parkingspace.services;

import com.parkingspace.models.ParkingTicket;

public interface ITicketService {
    ParkingTicket createTicket(ParkingTicket ticketInfo);

}
