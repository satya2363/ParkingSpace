package com.parkingspace.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.parkingspace.models.RegisteredUser;

public interface UserRepository extends CrudRepository<RegisteredUser, Long> {

    @Transactional
    @Modifying
    @Query("update RegisteredUser ru set ru.status=:status where ru.phoneNumber=:phoneNumber")
    void updateUser(@Param("status") String status, @Param("phoneNumber") long phoneNumber);

}
