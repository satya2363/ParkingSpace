package com.parkingspace.models;

import java.time.LocalTime;

import javax.persistence.Entity;

import com.parkingspace.utils.ParkingStatus;

@Entity
public class ParkingTicket {
    String        ticketNumber;
    LocalTime     issuedAt;
    LocalTime     startTime;
    LocalTime     endTime;
    LocalTime     payedAt;
    double        payedAmount;
    ParkingStatus status;
    int           floorNumber;
    int           spotNumber;
    String        barCode;
}
