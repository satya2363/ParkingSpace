package com.parkingspace.DTO;

public class SpotAvailabilityDTO {
    private int    floorId;
    private int    floorNumber;
    private int    totalSpots;
    private int    parkingLotId;
    private String spotsAvailable;
    private String spotType;
    private int    spotNumber;

    public SpotAvailabilityDTO() {
    }

    public SpotAvailabilityDTO(int floorId, int floorNumber, int totalSpots, int parkingLotId, String spotsAvailable, String isSpotFree, String spotType, int spotNumber) {
        this.floorId = floorId;
        this.totalSpots = totalSpots;
        this.setFloorNumber(floorNumber);
        this.parkingLotId = parkingLotId;
        this.spotsAvailable = spotsAvailable;
        this.spotType = spotType;
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

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public String getSpotsAvailable() {
        return spotsAvailable;
    }

    /**
     * @return the floorNumber
     */
    public int getFloorNumber() {
        return floorNumber;
    }

    /**
     * @param floorNumber
     *            the floorNumber to set
     */
    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

}
