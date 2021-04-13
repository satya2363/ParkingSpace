package com.parkingspace.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingspace.DTO.ParkingAvailabilityDTO;
import com.parkingspace.models.ParkingLot;
import com.parkingspace.models.ParkingSpot;
import com.parkingspace.repositories.ParkingRepository;

@Service
public class ParkingServiceImpl implements IParkingService {

    @Autowired
    EntityManagerFactory emf;

    @Autowired
    ParkingRepository    parkingRepo;

    @Override
    public List<ParkingLot> parkingLotQuery(String city, int zipcode) {
        return parkingRepo.getAllParkingLots(city, zipcode);
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
