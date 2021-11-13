package com.emse.spring.faircorp.api;

import java.util.List;
import java.util.stream.Collectors;

import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.WindowDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/window")
@CrossOrigin
@Transactional
public class WindowController{

    private final WindowDao windowDao;
    private final RoomDao roomDao;

    public WindowController(WindowDao windowDao, RoomDao roomDao){
        this.windowDao = windowDao;
        this.roomDao = roomDao;
    }

    @ApiOperation(value = "Getting all the windows")
    @GetMapping
    public List<WindowDto> findAll(){
        return windowDao.findAll().stream().map(WindowDto::new).collect(Collectors.toList());
    }

    @ApiOperation(value = "Return a window by id")
    @GetMapping(path = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        WindowDto window = windowDao.findById(id).map(WindowDto::new).orElse(null);
        if(window == null) {return new ResponseEntity(HttpStatus.NOT_FOUND);}
        else {return new ResponseEntity(window, HttpStatus.OK);}
    }

    @ApiOperation(value = "Change the status of the window")
    @PutMapping(path = "/{id}/switch")
    public ResponseEntity switchStatus(@PathVariable Long id){
        Window window = windowDao.findById(id).orElse(null);
        if(window == null) {return new ResponseEntity("404 - Not Found", HttpStatus.NOT_FOUND);}
        window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);
        return new ResponseEntity(new WindowDto(window), HttpStatus.OK);
    }

    @ApiOperation(value = "Getting the list of windows")
    @GetMapping(path = "/id-room/{roomId}")
    public List<WindowDto> findAllByRoomId(@PathVariable("roomId") Long id) {
        return windowDao.findWindowsByRoom(id).stream().map(WindowDto::new).collect(Collectors.toList());
    }
    
    @Deprecated
    @ApiOperation(value = "Creating a new window")
    @PostMapping
    public ResponseEntity create (@RequestBody WindowDto windowDto){
        Room room = roomDao.findById(windowDto.getRoomId().orElse(null));
        if(room == null){return new ResponseEntity("404 - Not Found", HttpStatus.NOT_FOUND);}
        Window window = null;
        if(windowDto.getId() == null){window = windowDao.save(new Window(room, windowDto.getName(), windowDto.getWindowStatus()));}
        else{window = windowDao.getOne(windowDto.getId());
        window.setWindowStatus(windowDto.getWindowStatus());}
    }

    @ApiOperation(value = "Deleting a window")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        windowDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
