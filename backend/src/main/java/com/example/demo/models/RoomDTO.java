package com.example.demo.models;

import java.util.Set;

public class RoomDTO{

    private String roomName;
    private Set<Message> messages;
    private Set<User> users;

    public RoomDTO(){

    }

    public RoomDTO(String roomName, Set<Message> messages, Set<User> users) {
        this.roomName = roomName;
        this.messages = messages;
        this.users = users;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
