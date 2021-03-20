package com.parkingspace.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.parkingspace.DTO.ParkingAvailabilityDTO;
import com.parkingspace.models.ParkingLot;

public interface ParkingRepository extends CrudRepository<ParkingLot, Integer> {
    @Query("select new com.parkingspace.DTO.ParkingAvailabilityDTO(pl.isFull, fl.totalSpots,fl.spotsAvailable,fl.id)  from ParkingLot as pl,Floor as fl where pl.id=:id AND fl.parkingLot.id = pl.id AND fl.number=:number")
    public ParkingAvailabilityDTO getparkingLotAvailability(@Param("id") int parkingLotId, @Param("number") int floorNumber);

    @Query("select pl.id,pl.isFull,pl.name,lo.city,lo.state,lo.street_name,lo.id from Location as lo, ParkingLot as pl where lo.city=:city AND lo.zipcode=:zipcode and lo.id = pl.address.id")
    public List<ParkingLot> getAllParkingLots(@Param("city") String city, @Param("zipcode") int zipcode);
}
