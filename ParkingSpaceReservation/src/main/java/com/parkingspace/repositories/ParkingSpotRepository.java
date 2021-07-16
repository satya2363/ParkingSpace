package com.parkingspace.repositories;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.parkingspace.DTO.SpotAvailabilityDTO;
import com.parkingspace.models.ParkingSpot;

public interface ParkingSpotRepository extends CrudRepository<ParkingSpot, Integer> {

    @Modifying
    @Transactional
    @Query("update ParkingSpot ps set ps.isFree =:isFree,ps.licenseNumber=:licenseNumber where ps.number=:spotNumber AND ps.floor.id=:floorId")
    public void updateSlot(@Param("isFree") String isFree, @Param("licenseNumber") String licenseNumber, @Param("spotNumber") int spotnumber, @Param("floorId") int floorId);

    @Query("select new com.parkingspace.DTO.SpotAvailabilityDTO( fl.id,fl.number,fl.totalSpots,fl.parkingLot.id,fl.spotsAvailable,spot.isFree,spot.type,spot.number) from ParkingLot as pl,Floor as fl,ParkingSpot as spot where pl.id=:id "
            + "AND fl.parkingLot.id = pl.id "
            + "AND fl.spotsAvailable='true' "
            + "AND spot.type=:spotType "
            + "AND spot.floor.id = fl.id "
            + "AND spot.isFree='true'")
    public Set<SpotAvailabilityDTO> getparkingSpotAvailability(@Param("id") int parkingLotId, @Param("spotType") String spotType);

    //    @Query("select new com.parkingspace.DTO.SpotAvailabilityDTO( fl.id,fl.totalSpots,fl.parkingLot.id,fl.spotsAvailable,stc.spotType,stc.count,spot.number) from ParkingLot as pl,Floor as fl,SpotTypeCount as stc,ParkingSpot as spot where pl.id=:id "
    //            + "AND fl.parkingLot.id = pl.id "
    //            + "AND fl.spotsAvailable='true' "
    //            + "AND pl.isFull='false'"
    //            + "AND stc.floor.id = fl.id "
    //            + "AND stc.spotType=:spotType "
    //            + "AND spot.floor.id = fl.id ")
    //    public Set<SpotAvailabilityDTO> getparkingSpotAvailability(@Param("id") int parkingLotId, @Param("spotType") String spotType);
}
