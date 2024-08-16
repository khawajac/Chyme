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
        boolean alreadyAssociated = user.getUserRooms().stream()
                .anyMatch(userRoom -> userRoom.getRoom().equals(room));

        // If not associated, create and save the UserRoom entity
        if (!alreadyAssociated) {
            UserRoom userRoom = new UserRoom(user, room, LocalDateTime.now());
            userRoomRepository.save(userRoom);
            // Also add it to the user's UserRooms set
            user.getUserRooms().add(userRoom);
            userRepository.save(user); // Save the user with the updated rooms
        }
    }

    public Optional<Set<UserRoom>> getSpecificUsersRooms(Long id){
        Optional<User> optionalUser = userService.getUserById(id);
        if(optionalUser.isPresent()){
            Set<UserRoom> usersRoom = optionalUser.get().getUserRooms();
            return Optional.of(usersRoom);
        }
        else return Optional.empty();
    }

    public void deleteUserRoom(Long id){
        //user leaves a room
        userRoomRepository.deleteById(id);
    }
}
