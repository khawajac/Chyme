package com.example.demo.controllers;

import com.example.demo.models.Message;
import com.example.demo.models.MessageRequest;
import com.example.demo.services.MessageService;
import com.example.demo.services.RoomService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    // Endpoint to send a message
    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest messageRequest) {
        try {
            Message message = messageService.sendMessage(messageRequest.getSenderId(),
                    messageRequest.getRecipientId(),
                    messageRequest.getContent());
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to get all messages in a room
    @GetMapping("/{roomId}")
    public ResponseEntity<List<Message>> getMessagesByRoom(@PathVariable Long roomId) {
        try {
            List<Message> messages = messageService.getMessagesByRoom(roomId);
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to get a message by ID
    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        try {
            Message message = messageService.getMessageById(id).orElseThrow();
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
