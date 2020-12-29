package com.parkingspace.models;

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
public class SpotTypeCount {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "handicapped")
    private int handicapped;

    @Column(name = "compact")
    private int compact;

    @Column(name = "large")
    private int large;

    @Column(name = "motor_bike")
    private int motorBike;

    @Column(name = "electric")
    private int electric;
}
