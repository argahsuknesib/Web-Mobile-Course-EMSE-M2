package com.emse.spring.faircorp.api;

import java.util.List;
import java.util.stream.Collectors;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/api/room")
@Transactional
public class RoomController {

    private final WindowDao windowDao;
    private final RoomDao roomDao;
    private final BuildingDao buildingDao;
    private final HeaterDao heaterDao;

    public RoomController(WindowDao windowDao, RoomDao roomDao, BuildingDao buildingDao,HeaterDao heaterDao){
        this.windowDao = windowDao;
        this.roomDao = roomDao;
        this.buildingDao = buildingDao;
        this.heaterDao = heaterDao;
    }

    @ApiOperation(value = "Getting the rooms in the system")
    @GetMapping
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @ApiOperation(value = "Room with all it's properties")
    @GetMapping(path = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        RoomDto room = roomDao.findById(id).map(RoomDto::new).orElse(null);
        if(room == null) {return new ResponseEntity("404 - Not Found", HttpStatus.NOT_FOUND);}
        return new ResponseEntity(room, HttpStatus.OK);
    }

    @ApiOperation(value = "Getting all the room with it's properties")
    @GetMapping(path = "/{id}/allTheRoom")
    public List<RoomDto> findAllByBuildingId(@PathVariable Long id) {
        return roomDao.findRoomByBuilding(id).stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @ApiOperation(value = "Changing the state of the window")
    @PutMapping(path = "/{id}/changeWindowStatus")
    public ResponseEntity changeWindowStatus(@PathVariable Long id){
        Room room = roomDao.findById(id).orElse(null);
        if (room == null) {return new ResponseEntity("404 - Not Found", HttpStatus.NOT_FOUND);}
        List<Window> windows = windowDao.findWindowsByRoom(room.getId());
        windows.forEach(w->{w.setWindowStatus(w.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);});
        return  new ResponseEntity(new RoomDto(room),HttpStatus.OK);
    }

    @ApiOperation(value = "Change the status of the heater")
    @PutMapping(path = "/{id}/changeHeaterStatus")
    public ResponseEntity changeHeaterStatus(@PathVariable Long id){
        Room room = roomDao.findById(id).orElse(null);
        if (room == null){return new ResponseEntity("404 - Not Found", HttpStatus.NOT_FOUND);}
        List<Heater> heaters = heaterDao.findAllHeaterByRoom(room.getId());
        heaters.forEach(w->{w.setHeaterStatus(w.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF: HeaterStatus.ON);});
        return new ResponseEntity(new RoomDto(room),HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a room")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteRoom(@PathVariable Long id){
        roomDao.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }




}
