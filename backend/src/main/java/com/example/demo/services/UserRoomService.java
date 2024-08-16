package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.models.UserRoom;
import com.example.demo.repositories.UserRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserRoomService {

    @Autowired
    UserRoomRepository userRoomRepository;

    @Autowired
    UserService userService;

    public List<UserRoom> findAllUsersRooms(){
        return userRoomRepository.findAll();
    }

    public Optional<UserRoom> findUserRoomById(Long id) {
        return userRoomRepository.findById(id);
    }

    public Optional<Set<UserRoom>> findSpecificUsersRooms(Long id){
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
