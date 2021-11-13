package com.emse.spring.faircorp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;

public class WindowDaoImplementation implements WindowDaoCustom {
    
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Window> findRoomOpenWindows(Long id) {
        String randomQuery = "select w from Window w where w.room.id = :id and w.windowStatus = :status";
        return entityManager.createQuery(randomQuery, Window.class).setParameter("id", id).setParameter("status", WindowStatus.OPEN).getResultList();
    }

    @Override
    public List<Window> findWindowsByRoom(Long roomId) {
        String randomQuery = "select w from Window w" +  " JOIN Room r ON r.id = w.room.id" + " WHERE r.id = :id " ;
        return entityManager.createQuery(randomQuery, Window.class).setParameter("id", roomId).getResultList();
    }

    
}