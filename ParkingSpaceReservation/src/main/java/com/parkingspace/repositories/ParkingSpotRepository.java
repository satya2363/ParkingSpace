package com.parkingspace.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.parkingspace.models.ParkingSpot;

public interface ParkingSpotRepository extends CrudRepository<ParkingSpot, Integer> {

    @Modifying
    @Transactional
    @Query("update ParkingSpot ps set ps.isFree =:isFree,ps.licenseNumber=:licenseNumber where ps.number=:spotNumber AND ps.floor.id=:floorId")
    public void updateSlot(@Param("isFree") String isFree, @Param("licenseNumber") String licenseNumber, @Param("spotNumber") int spotnumber, @Param("floorId") int floorId);
}
