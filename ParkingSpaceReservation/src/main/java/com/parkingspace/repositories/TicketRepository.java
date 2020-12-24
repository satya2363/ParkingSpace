package com.parkingspace.repositories;

import org.springframework.data.repository.CrudRepository;

import com.parkingspace.models.ParkingTicket;

public interface TicketRepository extends CrudRepository<ParkingTicket, Integer> {

}
