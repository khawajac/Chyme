import React from 'react';

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
    return <div>Loading conversations...</div>;
  }

  return (
    <div>
      <h2>Your Conversations</h2>
      <ul>
        {rooms.map((room) => (
          <li key={room.id} onClick={() => onSelectRoom(room.id)}>
            {room.name}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ConversationsTab;
