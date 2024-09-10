import React from 'react';
import "../App.css"

interface Room {
  id: number;
  name: string;
}

interface ConversationsTabProps {
  rooms: Room[];
  loading: boolean;
  onSelectRoom: (roomId: number) => void;
}

const ConversationsTab: React.FC<ConversationsTabProps> = ({ rooms, loading, onSelectRoom }) => {
  if (loading) {
    return <div className="loading">Loading conversations...</div>;
  }

  return (
    <div className="conversations-tab">
      <h2>Your Conversations</h2>
      {rooms.length === 0 ? (
        <p>No conversations found.</p>
      ) : (
        <ul className="room-list">
          {rooms.map((room) => (
            <li 
              key={room.id} 
              onClick={() => onSelectRoom(room.id)}
              className="room-item"
            >
              {room.name}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default ConversationsTab;