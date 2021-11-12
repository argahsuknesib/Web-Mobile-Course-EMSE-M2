package com.emse.spring.faircorp.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.emse.spring.faircorp.dto.WindowDto;
import javax.transaction.Transactional;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
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
@RequestMapping(value = "/api/windows")
@Transactional
public class WindowController {
    
    private final WindowDao windowDao;
    private final RoomDao roomDao;

    public WindowController(WindowDao windowDao, RoomDao roomDao){
        this.windowDao = windowDao;
        this.roomDao = roomDao;
    }

    @ApiOperation(value = "Get the list of windows")
    @GetMapping
    public List<WindowDto> findAll() {
        return windowDao.findAll().stream().map(WindowDto::new).collect(Collectors.toList());
    }

    @ApiOperation(value = "Request for a specific window")
    @GetMapping(path = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        WindowDto window = windowDao.findById(id).map(WindowDto::new).orElse(null);
        if (window == null) return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Closing/Opening the Window")
    @PutMapping(path = "/{id}/switch")
    public WindowDto switchStatus(@PathVariable Long id) {
        Window window = windowDao.findById(id).orElseThrow(IllegalArgumentException::new);
        window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);
        return new WindowDto(window);
    }

    @ApiOperation(value = "Creating a new Window Instance")
    @PostMapping // (8)
    public WindowDto create(@RequestBody WindowDto dto) {
        // WindowDto must always contain the window room
        Room room = roomDao.findById(dto.getRoomId()).orElse(null);
        if (room == null) {return new ResponseEntity("The room is not found", HttpStatus.NOT_FOUND);}
        Window window = null;
        // On creation id is not defined
        if (dto.getId() == null) {
            window = windowDao.save(Window(room, dto.getName(), dto.getWindowStatus()));
        }
        else {
            window = windowDao.getById(dto.getId());  // (9)
            window.setWindowStatus(dto.getWindowStatus());
        }
        return new WindowDto(window);
    }

    @ApiOperation(value = "Deleting a Window Instance")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        windowDao.deleteById(id);
    }
}
    
