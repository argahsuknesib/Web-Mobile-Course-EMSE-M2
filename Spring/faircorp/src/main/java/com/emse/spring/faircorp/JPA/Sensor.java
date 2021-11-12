/*

package com.emse.spring.faircorp.JPA;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "SP_SENSOR")
public class Sensor {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    private String description;
    
    @Column(name = "power")
    private Integer defaultPowerInWatt;

    @Transient
    private Integer notImportant;

    @Enumerated(EnumType.STRING)
    private PowerSource powerSource;

    public Sensor(){

    }

    public Sensor(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Integer getDefaultPowerInWatt() {
        return defaultPowerInWatt;
    }

    public void setDefaultPowerInWatt(Integer defaultPowerInWatt){
        this.defaultPowerInWatt = defaultPowerInWatt;
    }

    public Integer getNotImportant() {
        return notImportant;
    }

    public void setNotImportant(Integer notImportant){
        this.notImportant = notImportant;
    }

    public PowerSource getPowerSource() {
        return powerSource;
    }

    public void setPowerSource(PowerSource powerSource){
        this.powerSource = powerSource;
    }


}
*/