import React, { useState, useRef, useEffect } from 'react';

interface User {
  id: number;
  username: string;
  email: string;
}

interface Message {
  id: number;
  content: string;
  sender: User;
  timeStamp: string;
}

interface ChatRoomProps {
  messages: Message[];
  loading: boolean;
  onSendMessage: (content: string) => void;
  currentUserId: number | null;
}

const ChatRoom: React.FC<ChatRoomProps> = ({ messages, loading, onSendMessage, currentUserId }) => {
  const [newMessage, setNewMessage] = useState<string>('');
  const messagesEndRef = useRef<HTMLDivElement>(null);

  const handleSendMessage = () => {
    if (newMessage.trim()) {
      onSendMessage(newMessage.trim());
      setNewMessage('');
    }
  };

  const handleKeyPress = (e: React.KeyboardEvent<HTMLTextAreaElement>) => {
    if (e.key === 'Enter' && !e.shiftKey) {
      e.preventDefault();
      handleSendMessage();
    }
  };

  useEffect(() => {
    messagesEndRef.current?.scrollIntoView({ behavior: 'smooth' });
  }, [messages]);

  if (loading) {
    return <div>Loading messages...</div>;
  }

  return (
    <div className="chat-room">
      <h2>Chat Room</h2>
      <div className="message-list" style={{ height: '400px', overflowY: 'auto', marginBottom: '20px' }}>
        {messages.length === 0 ? (
          <div>No messages available</div>
        ) : (
          messages.map((message) => (
            <div key={message.id} className={`message ${message.sender.id === currentUserId ? 'sent' : 'received'}`}>
              <strong>{message.sender.username}:</strong> {message.content}
              <small className="timestamp">{new Date(message.timeStamp).toLocaleString()}</small>
            </div>
          ))
        )}
        <div ref={messagesEndRef} />
      </div>
      <div className="message-input">
        <textarea
          value={newMessage}
          onChange={(e) => setNewMessage(e.target.value)}
          onKeyPress={handleKeyPress}
          placeholder="Type your message..."
          rows={3}
        />
        <button onClick={handleSendMessage}>Send</button>
      </div>
    </div>
  );
};

export default ChatRoom;