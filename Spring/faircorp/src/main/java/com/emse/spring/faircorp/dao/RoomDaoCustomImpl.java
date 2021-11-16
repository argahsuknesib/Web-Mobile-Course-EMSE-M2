package com.emse.spring.faircorp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.emse.spring.faircorp.model.Room;

public class RoomDaoCustomImpl implements RoomDaoCustom {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Room findRoomByName(String name) {
        String random = "select r from Room where r.name = :name";
        return entityManager.createQuery(random, Room.class).setParameter("name", name).getSingleResult();
    }

    @Override
    public List<Room> findRoomByBuilding(Long id) {
        String randomQuery = "select r from Room r" + "JOIN Building b ON b.id = r.building.id" + "where b.id = :id";
        return entityManager.createQuery(randomQuery, Room.class).setParameter("id", id).getResultList();
    }

    
}
