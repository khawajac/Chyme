package com.example.demo.services;

import com.example.demo.models.Room;
import com.example.demo.models.User;
import com.example.demo.models.UserRoom;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserRoomService {

    @Autowired
    UserRoomRepository userRoomRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    public List<UserRoom> getAllUsersRooms(){
        return userRoomRepository.findAll();
    }

    // Method to associate a user with a room
    public void addUserToRoom(User user, Room room) {
        // Check if the UserRoom relationship already exists
        boolean alreadyAssociated = userRoomRepository.existsByUserAndRoom(user, room);

        // If not associated, create and save the UserRoom entity
        if (!alreadyAssociated) {
            UserRoom userRoom = new UserRoom(user, room, LocalDateTime.now());
            userRoomRepository.save(userRoom);
        }
    }

    public Set<Room> getSpecificUsersRooms(Long id) {
        return userRoomRepository.findRoomsByUserId(id);
    }

    public void deleteUserRoom(Long id) {
        // Find the UserRoom entry by ID
        UserRoom userRoom = userRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserRoom not found"));
        // Delete the UserRoom entry
        userRoomRepository.delete(userRoom);
    }
}
