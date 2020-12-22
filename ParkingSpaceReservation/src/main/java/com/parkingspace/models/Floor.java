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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Floor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private String number;

    @Column(name = "total_spots")
    private int total_spots;

    @Column(name = "spots_available")
    private boolean spots_available;

//    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "spot_type_count_id", referencedColumnName = "id")
    private int spotTypeCount;

}
