package com.parkingspace.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.parkingspace.DTO.SpotAvailabilityDTO;
import com.parkingspace.models.ParkingSpot;

public interface SpotRepository extends CrudRepository<ParkingSpot, Integer> {
    @Query("select new com.parkingspace.DTO.SpotAvailabilityDTO( fl.id,fl.totalSpots,fl.parkingLot.id,fl.spotsAvailable,stc.spotType,stc.count,spot.number) from ParkingLot as pl,Floor as fl,SpotTypeCount as stc,ParkingSpot spot where pl.id=:id "
            + "AND fl.parkingLot.id = pl.id "
            + "AND fl.spotsAvailable='true' "
            + "AND pl.isFull='false'"
            + "AND stc.floor.id = fl.id "
            + "AND stc.spotType=:spotType "
            + "AND spot.floor.id = fl.id ")
    public Set<SpotAvailabilityDTO> getparkingSpotAvailability(@Param("id") int parkingLotId, @Param("spotType") String spotType);
}
