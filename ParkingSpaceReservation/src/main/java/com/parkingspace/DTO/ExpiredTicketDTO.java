package com.parkingspace.DTO;

import java.time.LocalDateTime;

public class ExpiredTicketDTO {
    long          phoneNumber;
    LocalDateTime endTime;

    public ExpiredTicketDTO(long phoneNumber, LocalDateTime endTime) {
        this.phoneNumber = phoneNumber;
        this.endTime = endTime;
    }

    public long getphoneNumber() {
        return phoneNumber;
    }

    public void setphoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

}
