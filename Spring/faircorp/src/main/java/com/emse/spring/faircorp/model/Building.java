package com.emse.spring.faircorp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "BUILDING", uniqueConstraints = {
    @UniqueConstraint(columnNames = "name")
})
public class Building {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @GeneratedValue(strategy = GenerationType.AUTO)
    // @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMP_SEQ")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private Double temperature;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    private List<Room> rooms;

    public Building() {

    }

    public Building(Long id, String name, Double temperature, List<Room> rooms) {
        this.id = id;
        this.name = name;
        this.temperature = temperature;
        this.rooms = rooms;
    }

    public Building(String name, Double temperature) {
        this.name = name;
        this.temperature = temperature;
    }

    public Building(String name, Double temperature, List<Room> rooms) {
        this.name = name;
        this.temperature = temperature;
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
