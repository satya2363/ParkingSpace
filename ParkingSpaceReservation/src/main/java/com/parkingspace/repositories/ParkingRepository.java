package com.parkingspace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.parkingspace.DTO.ParkingAvailabilityDTO;
import com.parkingspace.models.ParkingLot;

public interface ParkingRepository extends JpaRepository<ParkingLot, Integer> {
    @Query("select new com.parkingspace.DTO.ParkingAvailabilityDTO(pl.isFull, fl.totalSpots,fl.spotsAvailable) fl.id from ParkingLot as pl,Floor as fl where pl.id=:id AND fl.parkingLot.id = pl.id AND fl.number=:number")
    public ParkingAvailabilityDTO parkingLotAvailable(@Param("id") int parkingLotId, @Param("number") String floorNumber);

    // @Modifying
    // @Query("update ParkingSpots ps set ps.isFree =:isFree,ps.licenseNumber=:licenseNumber where ps.number")
    //  public ParkingAvailabilityDTO parkingLotAvailable(@Param("id") int parkingLotId, @Param("number") String floorNumber);
}
