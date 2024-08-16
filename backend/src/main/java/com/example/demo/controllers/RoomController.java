package com.example.demo.controllers;

import com.example.demo.models.Room;
import com.example.demo.models.RoomDTO;
import com.example.demo.models.User;
import com.example.demo.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id){
        return roomService.getRoomById(id).map(room -> new ResponseEntity<>(room, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room, @RequestBody User sender, @RequestBody User recipient){
        Room createdRoom = roomService.saveRoom(room, sender, recipient);
        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/roomName")
    public ResponseEntity<Room> updateRoomName(@PathVariable Long id, @RequestBody RoomDTO roomDTO){
        try{
            return ResponseEntity.ok(roomService.updateRoomName(id, roomDTO.getRoomName()));
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id){
        try{
            roomService.deleteRoom(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
