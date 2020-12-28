package com.parkingspace.models;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.parkingspace.models.Floor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpot implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private String number;

    @Column(name = "is_free")
    private boolean isFree;

    @Column(name = "type")
    private String type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "floor_id", referencedColumnName = "id")
    private Floor floor;

    @Column(name = "license_number")
    private String licenseNumber;

    // TODO: understand why license number join wasnt needed?
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "vehicle_license_number", referencedColumnName = "vehicle_license_number")
//    private String vehicleLicenseNumber;
}
