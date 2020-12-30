package com.parkingspace.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpotTypeCount {

    private int id;
    private int handicapped;
    private int compact;
    private int large;
    private int motorBike;
    private int electric;
}
