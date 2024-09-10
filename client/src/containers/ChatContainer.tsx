import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import { RootState } from '../redux/store';
import ConversationsTab from '../components/ConversationsTab';
import ChatRoom from '../components/ChatRoom';

interface Room {
  id: number;
  name: string;
}

interface Message {
  id: number;
  senderId: number;
  content: string;
  timestamp: string;
}

const ChatContainer: React.FC = () => {
  const userId = useSelector((state: RootState) => state.user.id);
  console.log(userId); 
  const [rooms, setRooms] = useState<Room[]>([]);
  const [messages, setMessages] = useState<Message[]>([]);
  const [selectedRoomId, setSelectedRoomId] = useState<number | null>(null);
  const [loadingRooms, setLoadingRooms] = useState<boolean>(true);
  const [loadingMessages, setLoadingMessages] = useState<boolean>(false);


  useEffect(() => {
    const fetchRooms = async () => {
      try {
        const token = localStorage.getItem('token');
        if (!userId) {
          console.error('User ID is missing');
          return;
        }

        const response = await fetch(`http://localhost:8080/user-room/${userId}/rooms`, {
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
            "Accept": "application/json",
          },
        });

        if (response.ok) {
          const data = await response.json();
          setRooms(data);
        } else {
          console.error('Failed to fetch rooms');
        }
      } catch (error) {
        console.error('Error fetching rooms:', error);
      } finally {
        setLoadingRooms(false);
      }
    };

    fetchRooms();
  }, [userId]);

  const handleSelectRoom = (roomId: number) => {
    setSelectedRoomId(roomId);
    setMessages([]); // Clear messages when a new room is selected
  };

  const handleSendMessage = async (content: string) => {
    try {
      const token = localStorage.getItem('token');
      const response = await fetch('http://localhost:8080/messages/send', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
          "Accept": "application/json",
        },
        body: JSON.stringify({
          roomId: selectedRoomId,
          content,
        }),
      });

      if (response.ok) {
        const sentMessage = await response.json();
        setMessages((prevMessages) => [...prevMessages, sentMessage]);
      } else {
        console.error('Failed to send message');
      }
    } catch (error) {
      console.error('Error sending message:', error);
    }
  };

  return (
    <div style={{ display: 'flex' }}>
      <div style={{ flex: 1, padding: '20px' }}>
        <ConversationsTab
          rooms={rooms}
          loading={loadingRooms}
          onSelectRoom={handleSelectRoom}
        />
      </div>
      <div style={{ flex: 3, padding: '20px' }}>
        {selectedRoomId ? (
          <ChatRoom
            messages={messages}
            loading={loadingMessages}
            onSendMessage={handleSendMessage}
          />
        ) : (
          <div>Please select a conversation to start chatting.</div>
        )}
      </div>
    </div>
  );
};

export default ChatContainer;
