import React, { useState, useRef, useEffect } from 'react';

interface Message {
  id: number;
  senderId: number;
  content: string;
  timestamp: string;
}

interface ChatRoomProps {
  messages: Message[] | Message | null;
  loading: boolean;
  onSendMessage: (content: string) => void;
}

const ChatRoom: React.FC<ChatRoomProps> = ({ messages, loading, onSendMessage }) => {
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
  const messageArray = messages ? (Array.isArray(messages) ? messages : [messages]) : [];

  return (
    <div className="chat-room">
      <h2>Chat Room</h2>
      <div className="message-list" style={{ height: '400px', overflowY: 'auto', marginBottom: '20px' }}>
        {messageArray.length === 0 ? (
          <div>No messages available</div>
        ) : (
          messageArray.map((message) => (
            <div key={message.id} className={`message ${message.senderId === 1 ? 'sent' : 'received'}`}>
              <strong>{message.senderId === 1 ? 'You' : 'Them'}:</strong> {message.content}
              <small className="timestamp">{new Date(message.timestamp).toLocaleTimeString()}</small>
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