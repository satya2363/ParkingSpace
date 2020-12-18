package com.parkingspace.repositories;

import org.springframework.data.repository.CrudRepository;

import com.parkingspace.models.Location;

public interface LocationRepository extends CrudRepository<Location, Integer> {

}
