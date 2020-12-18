package com.parkingspace.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingspace.models.ParkingLot;

@Service
public class QueryService implements IQueryService {

    @Autowired
    EntityManagerFactory emf;

    @Override
    public List<ParkingLot> parkingLotQuery(String city, int zipcode) {
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("select pl.id,pl.isFull,pl.name,lo.city,lo.state,lo.street_name,lo.id from Location as lo, ParkingLot as pl where lo.city=" + city + " and lo.zipcode=" + zipcode + " and lo.id = pl.address.id\n" + "");
        @SuppressWarnings("unchecked")
        List<ParkingLot> parkingLots = query.getResultList();
        em.close();
        return parkingLots;
    }

}
