package com.emse.spring.faircorp.api;

import java.util.List;
import java.util.stream.Collectors;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.BuildingDto;
import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/building")
@CrossOrigin
@Transactional
public class BuildingController {
    @Autowired
    private final WindowDao windowDao;
    @Autowired
    private final RoomDao roomDao;
    @Autowired
    private final BuildingDao buildingDao;
    @Autowired
    private final HeaterDao heaterDao;


    BuildingController(WindowDao windowDao, RoomDao roomDao, BuildingDao buildingDao, HeaterDao heaterDao){
        this.windowDao = windowDao;
        this.roomDao = roomDao;
        this.buildingDao = buildingDao;
        this.heaterDao = heaterDao;
    }

    @ApiOperation("Return all the buildings available")
    @GetMapping
    public List<BuildingDto> findAll() {
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }

    @ApiOperation("Get a building with all of it's properties/details")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        BuildingDto building = buildingDao.findById(id).map(BuildingDto::new).orElse(null);
        if (building == null){return new ResponseEntity("404 - Not Found", HttpStatus.NOT_FOUND);}
        else{return new ResponseEntity(building, HttpStatus.OK);}
    }

    @ApiOperation("Delete a building with all of it's properties/details")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        buildingDao.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation("Change the all the status of window to either open/off")
    @PutMapping("/{id}/changeAllWindowStatus")
    public ResponseEntity changeAllWindowStatus(@PathVariable Long id, @PathVariable("status") Integer status) {
        Building building = buildingDao.findById(id).orElse(null);
        if (building == null) {return new ResponseEntity("404 - Not Found", HttpStatus.NOT_FOUND);}
        List<Room> rooms = roomDao.findRoomByBuilding(building.getId());
        if(rooms.size()<1)
            return new ResponseEntity("BUILDING HAS NO ROOM",HttpStatus.NOT_FOUND);
        rooms.forEach(r->{List<Window> windows = windowDao.findWindowsByRoom(r.getId());
            windows.forEach(w->{w.setWindowStatus(status == 0 ? WindowStatus.CLOSED: WindowStatus.OPEN);});
        });
        return new ResponseEntity(new BuildingDto(building),HttpStatus.OK);
    }

    @ApiOperation("Change all the heater status in the building")
    @PutMapping("/{id}/changeHeaterStatus")
    public ResponseEntity changeHeaterStatus(@PathVariable Long id, @RequestParam("status") Integer status) {
        Building building = buildingDao.findById(id).orElse(null);
        if (building == null) {return new ResponseEntity("404 - Not Found", HttpStatus.NOT_FOUND);}
        List<Room> rooms = roomDao.findRoomByBuilding(building.getId());
        if (rooms.size() < 1) {return new ResponseEntity("404 - Not Found", HttpStatus.NOT_FOUND);}
        rooms.forEach(room ->{
            List<Heater> heaters = heaterDao.findAllHeaterByRoom(room.getId());
            heaters.forEach(w->{w.setHeaterStatus(status == 0 ? HeaterStatus.OFF: HeaterStatus.ON);
            });
        });
        return new ResponseEntity(new BuildingDto(building), HttpStatus.OK);
    }

    @ApiOperation("Update the building and the temperature")
    @PutMapping("/{id}")
    public ResponseEntity updateBuilding(@PathVariable Long id, @RequestBody BuildingDto buildingDto){
        Building building = buildingDao.findById(buildingDto.getId()).orElse(null);
        if (building == null) {return new ResponseEntity("404 - Not Found", HttpStatus.NOT_FOUND);}
        building.setName(buildingDto.getName());
        building.setTemperature(buildingDto.getTemperature());
        return new ResponseEntity(new BuildingDto(building), HttpStatus.OK);
    }
}
