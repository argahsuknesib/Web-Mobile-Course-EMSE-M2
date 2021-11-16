package com.emse.spring.faircorp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM")
public class Room {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int floor;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private Double currentTemperature;

    @Column(nullable = true)
    private Double targetTemperature;
    
    @ManyToOne
    private Building building;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade= CascadeType.ALL)
    private List<Heater> listHeater;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade= CascadeType.ALL)   
    private List<Window> listWindow;


    public Room() {

    }

    public Room(Long id, Integer floor, String name){
        this.id = id;
        this.floor = floor;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public List<Heater> getListHeater() {
        return listHeater;
    }

    public void setListHeater(List<Heater> listHeater) {
        this.listHeater = listHeater;
    }

    public List<Window> getListWindow() {
        return listWindow;
    }

    public void setListWindow(List<Window> listWindow) {
        this.listWindow = listWindow;
    }

    
    
    
}
