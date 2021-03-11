package com.parkingspace.services;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingspace.DTO.ParkingAvailabilityDTO;
import com.parkingspace.models.ParkingLot;
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

}
