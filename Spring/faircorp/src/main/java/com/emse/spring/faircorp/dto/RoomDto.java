package com.emse.spring.faircorp.dto;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import com.sun.istack.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class RoomDto {
    private Long id;
    @NotNull
    private Integer floor;
    @NotNull
    private String name;
    private Double currentTemperature;
    @NotNull
    private Double targetTemperature;
    private List<HeaterDto> heaters;
    private List<WindowDto> windows;
    private Long buildingId;
    private Integer noOfOpenWindow;
    private Integer noOfOnHeater;

    public RoomDto(){

    }


    public RoomDto(Room room){
        this.id=room.getId();
        this.floor=room.getFloor();
        this.name=room.getName();
        this.currentTemperature=room.getCurrentTemperature();
        if(room.getTargetTemperature()!=null)
        this.targetTemperature=room.getTargetTemperature();
        if(room.getListHeater()!=null && room.getListHeater().size()>0)
        this. heaters=room.getListHeater().stream().map(HeaterDto::new).collect(Collectors.toList());
        if(room.getListWindow()!=null && room.getListWindow().size()>0)
        this.windows = room.getListWindow().stream().map(WindowDto::new).collect(Collectors.toList());
        if(room.getListHeater()!=null && room.getListHeater().size()>0)
        this.noOfOnHeater=setOnHeater(room);
        if(room.getListWindow()!=null && room.getListWindow().size()>0)
        this.noOfOpenWindow = setOpenWindow(room);

    }
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Integer getFloor() {
        return floor;
    }


    public void setFloor(Integer floor) {
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


    public List<HeaterDto> getHeaters() {
        return heaters;
    }


    public void setHeaters(List<HeaterDto> heaters) {
        this.heaters = heaters;
    }


    public List<WindowDto> getWindows() {
        return windows;
    }


    public void setWindows(List<WindowDto> windows) {
        this.windows = windows;
    }


    public Long getBuildingId() {
        return buildingId;
    }


    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }


    public Integer getNoOfOpenWindow() {
        return noOfOpenWindow;
    }


    public void setNoOfOpenWindow(Integer noOfOpenWindow) {
        this.noOfOpenWindow = noOfOpenWindow;
    }


    public Integer getNoOfOnHeater() {
        return noOfOnHeater;
    }


    public void setNoOfOnHeater(Integer noOfOnHeater) {
        this.noOfOnHeater = noOfOnHeater;
    }
 

    private Integer setOnHeater(Room room){
        List<Heater> heater= room.getListHeater().stream().filter(p -> p.getHeaterStatus().equals(HeaterStatus.ON)).collect(Collectors.toList());
        return heater.size();
    }

    private Integer setOpenWindow(Room room){
        List<Window> window= room.getListWindow().stream().filter(p -> p.getWindowStatus().equals(WindowStatus.OPEN)).collect(Collectors.toList());
        return window.size();
    }
}





// private Integer setOnHeater(Room room){
//     List<Heater> heater= room.getListHeater().stream().filter(p -> p.getHeaterStatus().equals(HeaterStatus.ON)).collect(Collectors.toList());
//     return heater.size();
// }

// private Integer setOpenWindow(Room room){
//     List<Window> window= room.getListWindow().stream().filter(p -> p.getWindowStatus().equals(WindowStatus.OPEN)).collect(Collectors.toList());
//     return window.size();
// }