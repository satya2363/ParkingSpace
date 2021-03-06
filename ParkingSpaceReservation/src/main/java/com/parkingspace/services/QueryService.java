package com.parkingspace.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingspace.DTO.ParkingAvailabilityDTO;
import com.parkingspace.models.ParkingLot;
import com.parkingspace.repositories.TicketRepository;

@Service
public class QueryService implements IQueryService {

    @Autowired
    EntityManagerFactory emf;

    @Autowired
    TicketRepository     ticketRepo;

    //TODO: may be try to put in a DTO
    @Override
    public List<ParkingLot> parkingLotQuery(String city, int zipcode) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select pl.id,pl.isFull,pl.name,lo.city,lo.state,lo.street_name,lo.id from Location as lo, ParkingLot as pl where lo.city=" + city + " and lo.zipcode=" + zipcode + " and lo.id = pl.address.id\n" + "");
        @SuppressWarnings("unchecked")
        List<ParkingLot> parkingLots = query.getResultList();
        em.close();
        return parkingLots;
    }

    @Override
    public ParkingAvailabilityDTO getParkingLotAvailability(int parkingId, int floorNumber) {
        /*EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select pl.isFull, fl.totalSpots,fl.spotsAvailable from ParkingLot as pl,Floor as fl where pl.id=" + 10 + " AND fl.parkingLot.id = pl.id AND fl.number=" + floorNumber);
        @SuppressWarnings("unchecked")
        ParkingAvailabilityDTO parkingLotDTOList = query.getResultList();
        System.out.println(parkingLotDTOList);
        em.close(); */
        //ticketRepo.findParkingAvailability(parkingId, floorNumber);
        return null;
    }

}
