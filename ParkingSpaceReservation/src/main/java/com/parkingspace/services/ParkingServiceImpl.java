package com.parkingspace.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.parkingspace.DTO.ParkingAvailabilityDTO;
import com.parkingspace.models.ParkingLot;
import com.parkingspace.models.ParkingSpot;
import com.parkingspace.repositories.ParkingRepository;

@Service
public class ParkingServiceImpl implements IParkingService {
    Logger               log = LoggerFactory.getLogger(TicketServiceImpl.class);
    @Autowired
    EntityManagerFactory emf;

    @Autowired
    ParkingRepository    parkingRepo;

    @Override
    @Async
    public CompletableFuture<List<ParkingLot>> parkingLotQuery(String city, int zipcode) {
        log.info("getting Parking Lots Asynchronously" + Thread.currentThread().getName());
        List<ParkingLot> parkingList = parkingRepo.getAllParkingLots(city, zipcode);
        return CompletableFuture.completedFuture(parkingList);
    }

    @Override
    public ParkingAvailabilityDTO getParkingLotAvailability(int parkingId, int floorNumber) {
        parkingRepo.getparkingLotAvailability(parkingId, floorNumber);
        return null;
    }

    @Override
    public List<ParkingSpot> spotQuery(int parkingLotId) {
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("select ps.isFree, ps.number, ps.type, ps.id from ParkingSpot as ps");
        //                + ", from Floor as fl where ps.isFree = true and fl.totalSpots > 0 "
        //                + "and ps.parkingLotId="+ parkingLotId + "and fl.id = ps.floorId\n" + "");
        @SuppressWarnings("unchecked")
        List<ParkingSpot> parkingSpots = query.getResultList();
        em.close();
        //parkingSpots.stream().map(mapper)
        return parkingSpots;
    }

}
