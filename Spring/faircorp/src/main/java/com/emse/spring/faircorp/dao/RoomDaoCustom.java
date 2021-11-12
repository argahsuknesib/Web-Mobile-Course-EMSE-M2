package com.emse.spring.faircorp.dao;

import java.util.List;

import com.emse.spring.faircorp.model.Room;

public interface RoomDaoCustom {
    Room findRoomByName(String name);
    List<Room> findRoomByBuilding(Long id);
}
