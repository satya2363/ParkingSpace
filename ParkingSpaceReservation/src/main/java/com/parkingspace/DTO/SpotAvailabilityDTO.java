package com.parkingspace.DTO;

public class SpotAvailabilityDTO {
    private int    floorId;
    private int    totalSpots;
    private int    parkingLotId;
    private String spotsAvailable;
    private String spotType;
    private int    spotTypeCount;
    private int    spotNumber;

    public SpotAvailabilityDTO(int floorId, int totalSpots, int parkingLotId, String spotsAvailable, String spotType, int spotTypeCount, int spotNumber) {
        this.floorId = floorId;
        this.totalSpots = totalSpots;
        this.parkingLotId = parkingLotId;
        this.spotsAvailable = spotsAvailable;
        this.spotType = spotType;
        this.spotTypeCount = spotTypeCount;
        this.spotNumber = spotNumber;

    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public int getTotalSpots() {
        return totalSpots;
    }

    public void setTotalSpots(int totalSpots) {
        this.totalSpots = totalSpots;
    }

    public int getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String isSpotsAvailable() {
        return spotsAvailable;
    }

    public void setSpotsAvailable(String spotsAvailable) {
        this.spotsAvailable = spotsAvailable;
    }

    public String getSpotType() {
        return spotType;
    }

    public void setSpotType(String spotType) {
        this.spotType = spotType;
    }

    public int getSpotTypeCount() {
        return spotTypeCount;
    }

    public void setSpotTypeCount(int spotTypeCount) {
        this.spotTypeCount = spotTypeCount;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public String getSpotsAvailable() {
        return spotsAvailable;
    }

}
