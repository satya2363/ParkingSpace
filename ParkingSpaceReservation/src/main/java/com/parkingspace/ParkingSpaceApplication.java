package com.parkingspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync(
        proxyTargetClass = true)
public class ParkingSpaceApplication {
    public static void main(String[] args) {
        //AsyncConfig asyncConfig = new AsyncConfig(2, 2, 100, "parking Lot Thread");
        SpringApplication.run(ParkingSpaceApplication.class, args);
    }
}
