
package com.example.demo.controllers;

import com.example.demo.models.Room;
import com.example.demo.models.User;
import com.example.demo.models.UserRoom;
import com.example.demo.repositories.UserRoomRepository;
import com.example.demo.services.RoomService;
import com.example.demo.services.UserRoomService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @Autowired
    UserService userService;

    @GetMapping("/rooms")
    public ResponseEntity<List<UserRoom>> getCurrentUserRooms(Authentication authentication) {
        try {
            String username = authentication.getName();
            User currentUser = userService.getUserByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            List<UserRoom> rooms = userRoomService.getRoomsByUserId(currentUser.getId());

            if (rooms.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(rooms, HttpStatus.OK);
        } catch (Exception e) {
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
