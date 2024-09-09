import React, { useState } from 'react';

interface Message {
  id: number;
  senderId: number;
  content: string;
  timestamp: string;
}

interface ChatRoomProps {
  messages: Message[];
  loading: boolean;
  onSendMessage: (content: string) => void;
}

const ChatRoom: React.FC<ChatRoomProps> = ({ messages, loading, onSendMessage }) => {
  const [newMessage, setNewMessage] = useState<string>('');

  const handleSendMessage = () => {
    if (newMessage.trim()) {
      onSendMessage(newMessage);
      setNewMessage(''); // Clear the input after sending
    }
  };

  if (loading) {
    return <div>Loading messages...</div>;
  }

  return (
    <div>
      <h2>Chat Room</h2>
      <div style={{ marginBottom: '20px' }}>
        {messages.map((message) => (
          <div key={message.id}>
            <strong>{message.senderId === 1 ? 'You' : 'Them'}:</strong> {message.content}
          </div>
        ))}
      </div>
      <textarea
        value={newMessage}
        onChange={(e) => setNewMessage(e.target.value)}
        placeholder="Type your message..."
      />
      <button onClick={handleSendMessage}>Send</button>
    </div>
  );
};

export default ChatRoom;
