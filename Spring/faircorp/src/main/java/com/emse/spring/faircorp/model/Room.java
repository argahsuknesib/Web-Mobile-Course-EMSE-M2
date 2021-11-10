package com.emse.spring.faircorp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM")
public class Room {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private int floor;

    @Column(nullable = false)
    private String name;

    private Double currentTemperature;

    private Double targetTemperature;

    // List<Heater> listHeater;
    // List<Window> listWindow;
    
    
    public Room(int floor, String name) {
        this.floor = floor;
        this.name = name;
    }

    public Room(){

    }

    
}
