package com.parkingspace.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.parkingspace.DTO.ExpiredTicketDTO;
import com.parkingspace.services.ITicketService;

@Configuration
@EnableScheduling
public class SchedulerTask {
    //private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Autowired
    ITicketService ticketService;

    @Scheduled(
            fixedRate = 15000)
    public void startSchedulerTask() {
        System.out.println(Thread.currentThread().getName() + " -- Current Thread Name");
        try {
            //Thread.sleep(10000);
            getExpiredCarSpots();
        }
        //catch (InterruptedException e) {
        //            // TODO Auto-generated catch block
        //            e.printStackTrace();
        //        } 
        catch (NullPointerException e) {
            e.printStackTrace();
        }

        //        final ScheduledFuture<?> taskHanlder = scheduler.scheduleAtFixedRate(new Runnable() {
        //            @Override
        //            public void run() {
        //                getExpiredCarSpots();
        //            }
        //        }, 0, 5, TimeUnit.SECONDS);
        //    }

        //        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        //
        //        Runnable task = new Runnable() {
        //            @Override
        //            public void run() {
        //                getExpiredCarSpots();
        //            }
        //        };
        //        scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
    }

    private void getExpiredCarSpots() {

        List<ExpiredTicketDTO> expiredTickets = ticketService.getExpiredParkingTickets();
        System.out.println("All Expired Tickets-->" + expiredTickets.size());
        //send Notification
        expiredTickets.stream().forEach(s -> System.out.println(s.getphoneNumber() + " ==> " + s.getEndTime()));

    }

}
