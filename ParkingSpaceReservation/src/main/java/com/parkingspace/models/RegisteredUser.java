package com.parkingspace.models;

import java.io.Serializable;

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
public class RegisteredUser implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @GeneratedValue(
            strategy = GenerationType.AUTO)
    @Column(
            name = "id")
    private int               id;
    @Id
    @Column(
            name = "phone_number")
    long                      phoneNumber;
    @Column(
            name = "status")
    String                    status;

}
