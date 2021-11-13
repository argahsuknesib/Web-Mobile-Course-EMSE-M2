package com.emse.spring.faircorp.api;

import java.util.List;
import java.util.stream.Collectors;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dto.HeaterDto;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;
import com.emse.spring.faircorp.model.Room;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/heater")
@CrossOrigin
@Transactional
public class HeaterController {
    
    private final HeaterDao heaterDao;
    private final RoomDao roomDao;
    
    public HeaterController(HeaterDao heaterDao, RoomDao roomDao){
        this.heaterDao = heaterDao;
        this.roomDao = roomDao;
    }

    @ApiOperation(value = "Requesting the list of all the heaters in the system.")
    @GetMapping
    public List<HeaterDto> findAll() {
        return heaterDao.findAll().stream().map(HeaterDto::new).collect(Collectors.toList());   
    }

    @ApiOperation(value = "Requesting the heater by using it's ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        HeaterDto heater = heaterDao.findById(id).map(HeaterDto::new).orElse(null);
        if (heater == null){
            return new ResponseEntity("404 - Not Found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(heater, HttpStatus.OK);
    }

    @ApiOperation(value = "Changing the status of the heaters in the system")
    @PutMapping(path = "/{id}/switch")
    public ResponseEntity switchStatus(@PathVariable Long id, @RequestParam("status") Integer status){
        Heater heater = heaterDao.findById(id).orElse(null);
        if (heater == null) {return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND) ;}
        heater.setHeaterStatus(status == 1 ? HeaterStatus.ON : HeaterStatus.OFF);
        return new ResponseEntity<>(new HeaterDto(heater), HttpStatus.OK);
    }

    @ApiOperation(value = "Creating a new heater instance")
    @PostMapping
    public ResponseEntity create(@RequestBody HeaterDto heaterdto){
        Room room = roomDao.findById(heaterdto.getRoomId()).orElse(null);
        if (room == null){return new ResponseEntity("404 - Not Found",HttpStatus.NOT_FOUND);}
        Heater heater = null;
        if(heaterdto.getId() == null){heater = heaterDao.save(new Heater(room, heaterdto.getName(), heaterdto.getHeaterStatus(), heaterdto.getPower()));}
        else{heater = heaterDao.getOne(heaterdto.getId());
            heater.setHeaterStatus(heaterdto.getHeaterStatus());}
        
        return new ResponseEntity<>(new HeaterDto(heater), HttpStatus.CREATED);
    }

    @ApiOperation(value = "All the heaters in a room")
    @GetMapping(path = "/in-room/{roomId}")
    public List<HeaterDto> findAllByRoomId(@PathVariable("roomId") Long id) {
        return heaterDao.findAllHeaterByRoom(id).stream().map(HeaterDto::new).collect(Collectors.toList());
    }

    @ApiOperation(value = "Delete a heater instance")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        heaterDao.deleteById(id);
        return new ResponseEntity<>("heater is now deleted", HttpStatus.OK);
    }








    
}
