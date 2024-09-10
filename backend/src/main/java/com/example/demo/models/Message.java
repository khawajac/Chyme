package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name ="content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @Setter
    @ManyToOne
    @JoinColumn(name = "room_id") // null initially, room should be created automatically
    @JsonBackReference
    private Room room;

    @Setter
    @Column(name="time_sent", nullable = false)
    private LocalDateTime timeStamp;

    public Message(String content, User sender, Room room, LocalDateTime timeStamp) {
        this.content = content;
        this.sender = sender;
        this.room = room;
        this.timeStamp = timeStamp;
    }

    public Message() {
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public User getSender() {
        return sender;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

}