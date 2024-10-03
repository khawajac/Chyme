import React, { useState, useEffect } from 'react';
import ConversationsTab from '../components/ConversationsTab';
import ChatRoom from '../components/ChatRoom';

interface Room {
  id: number;
  joinedAt: string;
  room: {
    id: number;
    roomName: string;
  };
  user: {
    id: number;
    username: string;
  };
}

interface Message {
  id: number;
  content: string;
  sender: number;
  timeStamp: string;
}

interface User {
  id: number;
  username: string;
}

const ChatContainer: React.FC = () => {
  const [rooms, setRooms] = useState<Room[]>([]);
  const [users, setUsers] = useState<User[]>([]); 
  const [messages, setMessages] = useState<Message[]>([]);
  const [selectedRoomId, setSelectedRoomId] = useState<number | null>(null);
  const [selectedUserId, setSelectedUserId] = useState<number | null>(null); 
  const [loadingRooms, setLoadingRooms] = useState<boolean>(true);
  const [loadingMessages, setLoadingMessages] = useState<boolean>(false);
  const [loadingUsers, setLoadingUsers] = useState<boolean>(false);
  const [newMessageContent, setNewMessageContent] = useState<string>('');
  const [showNewMessageWindow, setShowNewMessageWindow] = useState<boolean>(false);


  const fetchAllUsers = async () => {
    setLoadingUsers(true); 
    try {
        const token = localStorage.getItem('token');
        console.log(token); 
        if (!token) {
          console.error('No token found');
          return;
        }
        const response = await fetch(`http://localhost:8080/users`, {
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
          "Accept": "application/json"
        }
      }); 

      if (response.ok) {
        const data = await response.json();
        console.log(data); 
        setUsers(data); 
    } else {
      console.error('Failed to fetch users'); 
    }

  } catch (error) {
    console.error('Failed to fetch users'); 
  } finally {
    setLoadingUsers(false)
  }
}

  useEffect(() => {
    const fetchRooms = async () => {
      try {
        const token = localStorage.getItem('token');
        console.log(token); 
        if (!token) {
          console.error('No token found');
          return;
        }
        const response = await fetch(`http://localhost:8080/user-room/rooms`, {
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
            "Accept": "application/json",
          },
        });

        if (response.ok) {
          const data = await response.json();
          console.log(data); 
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

  const openNewMessageWindow = () => {
    setShowNewMessageWindow(true);
    fetchAllUsers(); 
  }

  const handleSelectRoom = (roomId: number) => {
    setSelectedRoomId(roomId);
  };

  console.log('Rooms in ChatContainer:', rooms); 

  useEffect(() => {
    const fetchMessages = async () => {
      if (selectedRoomId) {
        setLoadingMessages(true);
        setMessages([]); // Clear messages while loading
        try {
          const token = localStorage.getItem('token');
          const response = await fetch(`http://localhost:8080/messages/${selectedRoomId}`, {
            headers: {
              'Authorization': `Bearer ${token}`,
              'Content-Type': 'application/json',
              'Accept': 'application/json',
            },
          });

          if (response.ok) {
            const data = await response.json();
            setMessages(Array.isArray(data) ? data : [data]);
          } else {
            console.error('Failed to fetch messages');
            setMessages([]); 
          }
        } catch (error) {
          console.error('Error fetching messages:', error);
          setMessages([]); 
        } finally {
          setLoadingMessages(false);
        }
      }
    }
    fetchMessages();
  }, [selectedRoomId]);

  const handleSendMessage = async (content: string) => {
    if (!selectedRoomId) return;
    
    try {
      const token = localStorage.getItem('token');
      const response = await fetch('http://localhost:8080/messages/send', {
        method: 'POST',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
          "Accept": "application/json",
        },
        body: JSON.stringify({
          roomId: selectedRoomId,
          content,
        }),
      });

      if (response.ok) {
        const sentMessage = await response.json();
        setMessages((prevMessages) => 
          Array.isArray(prevMessages) 
            ? [...prevMessages, sentMessage] 
            : prevMessages 
              ? [prevMessages, sentMessage] 
              : [sentMessage]
        );
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
      <button type='button' onClick={handleNewMessage}>New Chatroom</button>
    </div>
  );
};

export default ChatContainer;
