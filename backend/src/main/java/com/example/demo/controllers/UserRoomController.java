package com.example.demo.controllers;

import com.example.demo.models.UserRoom;
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
    UserRoomService userRoomService;


//    // Endpoint to get specific user rooms by user ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Set<UserRoom>> getSpecificUsersRoom(@PathVariable Long id){
//        Optional<Set<UserRoom>> specificUsersRoom = userRoomService.getSpecificUsersRooms(id);
//        if(specificUsersRoom.isPresent()){
//            return ResponseEntity.ok(specificUsersRoom.get());
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsersRoom(@PathVariable Long id) {
        try {
            userRoomService.deleteUserRoom(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
