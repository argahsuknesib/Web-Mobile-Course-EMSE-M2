
package com.emse.spring.faircorp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.emse.spring.faircorp.model.Building;

public class BuildingDaoCustomImpl implements BuildingDaoCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Building findBuildingByName(String name) {
        String randomQuery = "select r from Building r where r.name = :name";
        return entityManager.createQuery(randomQuery, Building.class).setParameter("name", name).getSingleResult();
    }
    
}
