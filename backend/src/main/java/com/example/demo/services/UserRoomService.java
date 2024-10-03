package com.example.demo.services;

import com.example.demo.models.Room;
import com.example.demo.models.User;
import com.example.demo.models.UserRoom;
import com.example.demo.repositories.UserRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserRoomService {

    @Autowired
    UserRoomRepository userRoomRepository;

    public List<UserRoom> getAllUsersRooms(){
        return userRoomRepository.findAll();
    }


    public List<UserRoom> getUserRoomsByRoomId(Long roomId) {
        return userRoomRepository.findByRoomId(roomId);
    }

    public List<UserRoom> getRoomsByUserId(Long userId) {
        return userRoomRepository.findByUserId(userId);
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

    public void removeUserFromRoom(Long userId, Long roomId) {
        Optional<UserRoom> userRoomOptional = userRoomRepository.findByUserIdAndRoomId(userId, roomId);
        if (userRoomOptional.isPresent()) {
            UserRoom userRoom = userRoomOptional.get();
            userRoomRepository.delete(userRoom);
        } else {
            throw new RuntimeException("User or Room not found");
        }
    }
}