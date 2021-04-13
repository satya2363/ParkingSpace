package com.parkingspace.DTO;

public class ParkingAvailabilityDTO {

    private String isFull;
    private int    totalSpots;
    private String spotsAvailable;
    private int    floorId;

    public ParkingAvailabilityDTO(String isFull, int totalSpots, String spotsAvailable, int floorId) {
        this.isFull = isFull;
        this.spotsAvailable = spotsAvailable;
        this.totalSpots = totalSpots;
        this.floorId = floorId;

    }

    public String getIsFull() {
        return isFull;
    }

    public void setIsFull(String isFull) {
        this.isFull = isFull;
    }

    public int getTotalSpots() {
        return totalSpots;
    }

    public void setTotalSpots(int totalSpots) {
        this.totalSpots = totalSpots;
    }

    public String getSpotsAvailable() {
        return spotsAvailable;
    }

    public void setSpotsAvailable(String spotsAvailable) {
        this.spotsAvailable = spotsAvailable;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }
}
