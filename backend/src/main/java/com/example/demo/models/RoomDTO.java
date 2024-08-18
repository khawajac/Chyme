package com.example.demo.models;

import java.util.List;

public class RoomDTO {

    private String roomName;
    private List<UserDTO> users;

    public RoomDTO() {
    }

    public RoomDTO(String roomName, List<UserDTO> users) {
        this.roomName = roomName;
        this.users = users;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
