package com.example.demo.services;

import com.example.demo.models.Message;
import com.example.demo.models.Room;
import com.example.demo.models.User;
import com.example.demo.models.UserRoom;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoomRepository userRoomRepository;

    public Message sendMessage(Long senderId, Long recipientId, String content) {

        // Fetch sender and recipient users
        User sender = userRepository.findById(senderId).orElseThrow();
        User recipient = userRepository.findById(recipientId).orElseThrow();

        // Find or create a room
        Room room = findOrCreateRoom(sender, recipient);

        // Create and save the message
        Message message = new Message(content, sender, room, LocalDateTime.now());
        messageRepository.save(message);

        return message;
    }

    // Find an existing room or create a new one
    private Room findOrCreateRoom(User sender, User recipient) {
        Room existingRoom = findExistingRoomBetweenUsers(sender, recipient);

        if (existingRoom != null) {
            return existingRoom;
        }

        // Create a new room if no existing room is found
        Room newRoom = new Room();
        newRoom.setRoomName("Chat between " + sender.getUsername() + " and " + recipient.getUsername());
        Room savedRoom = roomService.saveRoom(newRoom);

        // Create and save UserRoom entries for both users
        userRoomRepository.save(new UserRoom(sender, savedRoom, LocalDateTime.now()));
        userRoomRepository.save(new UserRoom(recipient, savedRoom, LocalDateTime.now()));

        return savedRoom;
    }

    // Check if a room exists between the two users
    private Room findExistingRoomBetweenUsers(User sender, User recipient) {
        return userRoomRepository.findCommonRoomBetweenUsers(sender.getId(), recipient.getId())
                .orElse(null);
    }

    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    public List<Message> getMessagesByRoom(Long roomId) {
        return messageRepository.findByRoomId(roomId);
    }
}