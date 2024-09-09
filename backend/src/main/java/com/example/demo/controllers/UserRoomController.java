
package com.example.demo.controllers;

import com.example.demo.models.Room;
import com.example.demo.models.UserRoom;
import com.example.demo.repositories.UserRoomRepository;
import com.example.demo.services.RoomService;
import com.example.demo.services.UserRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user-room")
public class UserRoomController {

    @Autowired
    private UserRoomRepository userRoomRepository;

    @Autowired
    private UserRoomService userRoomService;

    @Autowired
    RoomService roomService;

    @GetMapping("/{userId}/rooms")
    public ResponseEntity<List<Room>> getAllUsersRooms(@PathVariable Long userId) {
        try {
            // Retrieve the list of UserRoom entities for the given userId
            List<UserRoom> userRooms = userRoomService.getRoomsByUserId(userId);

            // Extract the Room objects from the UserRoom entities
            List<Room> rooms = userRooms.stream()
                    .map(UserRoom::getRoom)
                    .collect(Collectors.toList());

            // Check if the list of rooms is empty
            if (rooms.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            // Return the list of rooms with a 200 OK status
            return new ResponseEntity<>(rooms, HttpStatus.OK);
        } catch (Exception e) {
            // Handle any exceptions and return a 500 Internal Server Error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
