package com.parkingspace.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.parkingspace.DTO.ExpiredTicketDTO;
import com.parkingspace.models.ParkingTicket;

public interface TicketRepository extends CrudRepository<ParkingTicket, Integer> {
    @Query("select new com.parkingspace.DTO.ExpiredTicketDTO(pt.phoneNumber,pt.endTime)  from ParkingTicket pt where CURRENT_DATE() > pt.endTime")
    public List<ExpiredTicketDTO> getExpiredParkingTickets();

}
