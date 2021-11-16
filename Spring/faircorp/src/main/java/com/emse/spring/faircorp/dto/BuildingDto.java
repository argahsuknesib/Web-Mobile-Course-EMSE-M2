package com.emse.spring.faircorp.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.emse.spring.faircorp.model.Building;

public class BuildingDto {
    

    private Long id;
    private String name;
    private double temperature;
    private List<RoomDto> rooms;
    
    public BuildingDto(){
        
    }

    public BuildingDto(Building building){
        this.id = building.getId();
        this.name = building.getName();
        this.temperature = building.getTemperature();
        if(building.getRooms() != null && building.getRooms().size() > 0){
         this.rooms = building.getRooms().stream().map(RoomDto::new).collect(Collectors.toList());   
        }
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

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public List<RoomDto> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDto> rooms) {
        this.rooms = rooms;
    }

}
