package com.emse.spring.faircorp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;

public class HeaterDaoCustomImpl implements HeaterDaoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Heater> findRoomOnHeater(Long id) {
        String randomQuery = "select h from Heater h where h.room.id = :id and h.heaterStatus = :status";
        return entityManager.createQuery(randomQuery, Heater.class).setParameter("id", id).setParameter("status", HeaterStatus.ON).getResultList();
    }

    @Override
    public List<Heater> findRoomOffHeater(Long id) {
        String randomQuery = "select h from Heater h where h.room.id = :id and h.heaterStatus = :status";
        return entityManager.createQuery(randomQuery, Heater.class).setParameter("id", id).setParameter("status", HeaterStatus.OFF).getResultList();
        
    }

    @Override
    public Integer deleteHeaterByRoom(Long id) {
        String randomQuery = "delete from Heater h where h.room.id = :id";
        return entityManager.createQuery(randomQuery, Heater.class).setParameter("id", id).executeUpdate();
    }

    @Override
    public List<Heater> findAllHeaterByRoom(Long roomId) {
        String randomQuery = "select h from Heater h" + "JOIN Room r ON r.id = h.room.id" + "JOIN Building b ON b.id = r.building.id" + "WHERE b.id = :id ";
        return entityManager.createQuery(randomQuery, Heater.class).setParameter("id", roomId).getResultList();
    }

    @Override
    public List<Heater> findALlHeaterByBuilding(Long buildingId) {
        String randomQuery = "select h from Heater" + "JOIN Room r ON r.id = h.room.id" + "where r.id = :id";
        return entityManager.createQuery(randomQuery, Heater.class).setParameter("id", buildingId).getResultList();
    }
    
}
