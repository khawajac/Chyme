package com.example.demo.services;


import com.example.demo.exceptions.RoomNotFoundException;
import com.example.demo.models.Room;
import com.example.demo.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public Room saveRoom(Room room) {
        validateRoom(room);
        return roomRepository.save(room);
    }

    public Optional<Room> getRoomById(Long id){
        return roomRepository.findById(id);
    }

    public Room updateRoomName(Long id, String roomName){
        validateRoomName(roomName);
        Room room = getRoomById(id).orElseThrow(() -> new RoomNotFoundException(id));
        room.setRoomName(roomName);
        return roomRepository.save(room);
    }

    public void deleteRoom(Long id){
        if(!roomRepository.existsById(id)){
            throw new RoomNotFoundException(id);
        }
        roomRepository.deleteById(id);
    }

    private void validateRoom(Room room){
        if(room == null){
            throw new IllegalArgumentException("Room cannot be null");
        }
        validateRoomName(room.getRoomName());
    }

    private void validateRoomName(String roomName){
        if(!StringUtils.hasText(roomName)){
            throw new IllegalArgumentException("Room name cannot be empty or null");
        }
    }
}
