package com.emse.spring.faircorp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;

public class WindowDaoCustomImpl implements WindowDaoCustom {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Window> findRoomOpenWindows(Long id) {
        String randomQuery = "select w from Window w WHERE w.room.id = :id AND w.windowStatus= :status";
        return entityManager.createQuery(randomQuery, Window.class).setParameter("id", id).setParameter("status", WindowStatus.OPEN).getResultList();
    }

    @Override
    public List<Window> findWindowsByRoom(Long roomId) {
        String randomQuery = "select w from Window w" +  " JOIN Room r ON r.id = w.room.id" + " WHERE r.id = :id " ;
        return entityManager.createQuery(randomQuery, Window.class).setParameter("id", roomId).getResultList();
    }
 
    @Override
    public List<Window> findWindowsByBuilding(Long buildingId) {
        String randomQuery = "select w from Window w " + " JOIN Room r ON r.id = w.room.id" + " JOIN Building b ON b.id = r.building.id" +  " WHERE b.id = :id " ;
        return entityManager.createQuery(randomQuery, Window.class).setParameter("id", buildingId).getResultList();
        // return null;
    }
   

    
}
