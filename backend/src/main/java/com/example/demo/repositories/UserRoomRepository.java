package com.example.demo.repositories;

import com.example.demo.models.Room;
import com.example.demo.models.RoomDTO;
import com.example.demo.models.User;
import com.example.demo.models.UserRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRoomRepository extends JpaRepository<UserRoom, Long> {

    @Query("SELECT sender.room FROM UserRoom sender " +
            "JOIN UserRoom recipient ON sender.room.id = recipient.room.id " +
            "WHERE sender.user.id = :senderId AND recipient.user.id = :recipientId")
    Optional<Room> findCommonRoomBetweenUsers(@Param("senderId") Long senderId, @Param("recipientId") Long recipientId);

    // Check if a UserRoom entry exists for a specific user and room
    boolean existsByUserAndRoom(User user, Room room);

    // Find a room by Room ID and User ID
    Optional<UserRoom> findByUserIdAndRoomId(Long userId, Long roomId);

    List<UserRoom> findByRoomId(Long roomId);




}