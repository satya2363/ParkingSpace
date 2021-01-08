package com.parkingspace.models;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
            name = "parking_lot_id")
    int       parkingLotId;

    @Column(
            name = "parking_lot_name")
    String    parkingLotName;

    @Column(
            name = "floor_number")
    String    floorNumber;
    @Column(
            name = "spot_number")
    int       spotNumber;
    @Column(
            name = "bar_code")
    String    barCode;

}
