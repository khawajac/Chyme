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
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("/{roomId}/users")
    public ResponseEntity<List<String>> getUsernamesByRoomId(@PathVariable Long roomId) {
        Optional<Room> optionalRoom = roomService.getRoomById(roomId);
        if (optionalRoom.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Room room = optionalRoom.get();
        List<String> usernames = roomService.getUsernamesByRoomId(roomId);
        if (usernames.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usernames, HttpStatus.OK);
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