package com.parkingspace.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.parkingspace.models.Floor;

public interface FloorRepository extends CrudRepository<Floor, Integer> {

    @Transactional
    @Modifying
    @Query("update Floor fl set fl.spotsAvailable=:spotsAvailable, fl.totalSpots=:totalSpots where fl.number=:floorNumber AND fl.parkingLot.id=:parkingId")
    public void updateFloor(@Param("spotsAvailable") String spotsAvailable, @Param("floorNumber") int floorNumber, @Param("totalSpots") int totalSpots, @Param("parkingId") int parkingLotId);
}
