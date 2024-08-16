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

    @Query("SELECT ur1.room FROM UserRoom ur1 " +
            "JOIN UserRoom ur2 ON ur1.room.id = ur2.room.id " +
            "WHERE ur1.user.id = :user1Id AND ur2.user.id = :user2Id")
    Optional<Room> findCommonRoomBetweenUsers(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id);

    // Check if a UserRoom entry exists for a specific user and room
    boolean existsByUserAndRoom(User user, Room room);

    // Find all rooms associated with a specific user
    List<RoomDTO> findRoomsByUserId(Long userId);

    // Find a room by Room ID and User ID
    Optional<UserRoom> findByUserIdAndRoomId(Long userId, Long roomId);



}