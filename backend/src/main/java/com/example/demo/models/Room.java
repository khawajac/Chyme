//package com.example.demo.models;
//
//
//import jakarta.persistence.*;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "rooms")
//public class Room {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(name = "room_name", nullable = false, length = 100)
//    private String roomName;
//
//    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Message> messages = new HashSet<>();
//
//    @ManyToMany(mappedBy = "rooms")
//    private Set<User> users = new HashSet<>();
//
//    public Room(String roomName, Set<Message> messages, Set<User> users) {
//        this.roomName = roomName;
//        this.messages = messages;
//        this.users = users;
//    }
//
//    public Room() {
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public String getRoomName() {
//        return roomName;
//    }
//
//    public void setRoomName(String roomName) {
//        this.roomName = roomName;
//    }
//
//    public Set<Message> getMessages() {
//        return messages;
//    }
//
//    public void setMessages(Set<Message> messages) {
//        this.messages = messages;
//    }
//
//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
//}
//
