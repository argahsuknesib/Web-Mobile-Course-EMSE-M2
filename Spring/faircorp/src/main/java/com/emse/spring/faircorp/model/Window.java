package com.emse.spring.faircorp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RWINDOW")
public class Window {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WindowStatus windowStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    private Room room;

    public Window(){

    }

    public Window(String name, WindowStatus status, Room room) {
        this.name = name;
        this.windowStatus = status;
        this.room = room;
    }

    public Window(Room room, String name, WindowStatus status) {
        this.room  =room;
        this.windowStatus = status;
        this.name = name;
    }

    public Window(String name, WindowStatus status) {
        this.windowStatus = status;
        this.name = name;
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

    public WindowStatus getWindowStatus() {
        return windowStatus;
    }

    public void setWindowStatus(WindowStatus windowStatus) {
        this.windowStatus = windowStatus;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    

}
