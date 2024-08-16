
package com.example.demo.controllers;

import com.example.demo.models.Room;
import com.example.demo.models.UserRoom;
import com.example.demo.repositories.UserRoomRepository;
import com.example.demo.services.UserRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user-room")
public class UserRoomController {

    @Autowired
    private UserRoomRepository userRoomRepository;

    @Autowired
    private UserRoomService userRoomService;


    // Endpoint to get specific user rooms by user ID
    @GetMapping("/{id}")
    public ResponseEntity<List<Room>> getSpecificUsersRoom(@PathVariable Long id){
        Optional<List<Room>> specificUsersRooms = Optional.ofNullable(userRoomService.getSpecificUsersRooms(id));
        return specificUsersRooms.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @DeleteMapping("/{userId}/rooms/{roomId}")
    public ResponseEntity<Void> removeUserFromRoom(@PathVariable Long userId, @PathVariable Long roomId){
        try {
            userRoomService.removeUserFromRoom(userId, roomId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
