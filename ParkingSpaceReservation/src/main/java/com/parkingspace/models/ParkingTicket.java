package com.parkingspace.models;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ParkingTicket {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO)
    @Column(
            name = "id")
    int       id;
    @Column(
            name = "ticket_number")
    String    ticketNumber;
    @Column(
            name = "issued_at")
    LocalTime issuedAt;
    @Column(
            name = "start_time")
    LocalTime startTime;
    @Column(
            name = "end_time")
    LocalTime endTime;
    @Column(
            name = "payed_at")
    LocalTime payedAt;
    @Column(
            name = "payed_amount")
    double    payedAmount;
    @Column(
            name = "status")
    String    status;
    @Column(
            name = "floor_number")
    int       floorNumber;
    @Column(
            name = "spot_number")
    int       spotNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public LocalTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getPayedAt() {
        return payedAt;
    }

    public void setPayedAt(LocalTime payedAt) {
        this.payedAt = payedAt;
    }

    public double getPayedAmount() {
        return payedAmount;
    }

    public void setPayedAmount(double payedAmount) {
        this.payedAmount = payedAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    @Column(
            name = "bar_code")
    String barCode;
}
