package com.parkingspace.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLot implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO)
    @Column(
            name = "id")
    private int               id;

    @Column(
            name = "name")
    private String            name;

    @OneToOne(
            cascade = CascadeType.ALL)
    @JoinColumn(
            name = "address_id",
            referencedColumnName = "id")
    private Location          address;

    @Column(
            name = "is_full")
    private String            isFull;

    @OneToMany(
            mappedBy = "parkingLot")
    private Set<Floor>        floors;
}
