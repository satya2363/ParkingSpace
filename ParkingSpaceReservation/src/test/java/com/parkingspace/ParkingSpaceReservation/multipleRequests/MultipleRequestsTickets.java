package com.parkingspace.ParkingSpaceReservation.multipleRequests;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.parkingspace.Exceptions.NoResultForQueryException;
import com.parkingspace.models.ParkingTicket;
import com.parkingspace.services.TicketServiceImpl;
import com.parkingspace.utils.RandomStringGenerator;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MultipleRequestsTickets implements Runnable, IMultipleRequests {

    List<ParkingTicket> ticketList;
    String[]            parkingLotNames = { "Keebler-O'Hara",
            "Hansen-Purdy",
            "Hilpert, Mills and Waters",
            "Buckridge and Sons",
            "Rath Group",
            "Spencer, Rogahn and Larkin",
            "Wehner, Hermiston and Bashirian",
            "Homenick Group",
            "Crooks, Willms and Goldner",
            "Wintheiser, Wilderman and Doyle" };
    String[]            spotTypes       = { "COMPACT", "LARGE", "MOTOR_BIKE", "ELECTRIC", "HANDICAPPED" };

    @Autowired
    TicketServiceImpl   ticketService;

    public MultipleRequestsTickets() {
        ticketList = new ArrayList<ParkingTicket>();
        ticketService = new TicketServiceImpl();
    }

    @Test
    public void createTicketFromMultipleUsers() {
        generateObjects(100);
        //ticketList.stream().forEach(tick -> System.out.println(tick.getId() + "--" + tick.getParkingLotName() + "--" + tick.getParkingLotId() + "--" + tick.getLicenseNumber() + "--" + tick.getSpotType()));
        ticketList.stream().parallel().forEach(ticket -> {
            try {
                ticketService.createTicket(ticket);
            } catch (NoResultForQueryException e) {
                e.printStackTrace();
            }
        });
    }

    //generate ticket object
    //hit the create ticket function with multiple threads
    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

    @Override
    public void generateObjects(int ticketCount) {
        for (int i = 1; i <= ticketCount; i++) {
            ParkingTicket ticket = new ParkingTicket();
            Random rand = new Random();
            int randSpotNumber = rand.nextInt(4);
            int randParkingLotId = rand.nextInt(9);
            int randPhoneNumber = rand.nextInt(10);
            LocalDateTime dateTime = LocalDateTime.now();
            ticket.setId(i);
            ticket.setTicketNumber(i + "");
            ticket.setStatus("ACTIVE");
            ticket.setParkingLotName(parkingLotNames[randParkingLotId]);
            ticket.setParkingLotId(randParkingLotId + 1);
            ticket.setPayedAmount(5.00);
            ticket.setLicenseNumber(getRandomString(6));
            ticket.setSpotType(spotTypes[randSpotNumber]);
            ticket.setDuration(2);
            ticket.setStartTime(dateTime);
            ticket.setIssuedAt(dateTime);
            ticket.setPayedAt(dateTime);
            ticket.setPhoneNumber(randPhoneNumber);
            ticketList.add(ticket);
        }

    }

    private String getRandomString(int lengthOfString) {
        RandomStringGenerator randString = new RandomStringGenerator();
        String str = randString.getAlphaNumericCode(lengthOfString);
        return str;
    }

}
