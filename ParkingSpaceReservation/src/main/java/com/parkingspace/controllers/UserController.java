package com.parkingspace.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.parkingspace.Exceptions.NoResultForQueryException;
import com.parkingspace.models.RegisteredUser;
import com.parkingspace.repositories.UserRepository;

@RestController
public class UserController {
    Logger                log = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    public UserRepository userRepo;

    @PostMapping(
            path = "/user/register")
    public @ResponseBody RegisteredUser registerUser(@RequestBody RegisteredUser user) throws NoResultForQueryException {
        return userRepo.save(user);
    }

}
