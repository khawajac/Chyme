package com.example.demo.models;

import java.util.Set;

public class RoomDTO{

    private String roomName;

    public RoomDTO(){

    }

    public RoomDTO(String roomName, Set<Message> messages, Set<User> users) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

}