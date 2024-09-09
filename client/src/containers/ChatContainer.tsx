import React, { useState, useEffect } from 'react';
import ConversationsTab from '../components/ConversationsTab.tsx';
import ChatRoom from '../components/ChatRoom.tsx';

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
  const [rooms, setRooms] = useState<Room[]>([]);
  const [messages, setMessages] = useState<Message[]>([]);
  const [selectedRoomId, setSelectedRoomId] = useState<number | null>(null);
  const [loadingRooms, setLoadingRooms] = useState<boolean>(true);
  const [loadingMessages, setLoadingMessages] = useState<boolean>(false);

  useEffect(() => {
    const fetchRooms = async () => {
      try {
        const token = localStorage.getItem('token'); // Assuming token is stored in localStorage
        const response = await fetch('/usersrooms/{userId}/rooms', {
          headers: {
            'Authorization': `Bearer ${token}`,
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
  }, []);

  useEffect(() => {
    if (selectedRoomId !== null) {
      const fetchMessages = async () => {
        setLoadingMessages(true);
        try {
          const token = localStorage.getItem('token'); // Assuming token is stored in localStorage
          const response = await fetch(`/api/your-endpoint/rooms/${selectedRoomId}/messages`, {
            headers: {
              'Authorization': `Bearer ${token}`,
            },
          });

          if (response.ok) {
            const data = await response.json();
            setMessages(data);
          } else {
            console.error('Failed to fetch messages');
          }
        } catch (error) {
          console.error('Error fetching messages:', error);
        } finally {
          setLoadingMessages(false);
        }
      };

      fetchMessages();
    }
  }, [selectedRoomId]);

  const handleSelectRoom = (roomId: number) => {
    setSelectedRoomId(roomId);
    setMessages([]); // Clear messages when a new room is selected
  };

  const handleSendMessage = async (content: string) => {
    try {
      const token = localStorage.getItem('token'); // Assuming token is stored in localStorage
      const response = await fetch('/api/messages/send', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
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
