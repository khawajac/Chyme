# Chyme - Chatroom
## URL

## Summary

Chyme is a backend application for a chatroom system designed to facilitate real-time communication among users. It supports user registration, authentication, and chat management features. Users can create and join chatrooms, send and receive messages, and manage their profiles. The system is optimized for handling multiple concurrent users and ensuring secure communication.

## Backend Tech Stack

- **Java**
- **Spring Boot**
- **PostgreSQL**
- **WebSocket** (for real-time communication)

## How to Run This Locally

1. **Install PostgreSQL**
   - Via Homebrew:
     ```sh
     brew install postgresql
     ```
   - Or via [Postgres.app](https://postgresapp.com)

2. **Create a Database**
   ```sh
   createdb chyme_db

## MVP Key Functionality

### Models

- User: Represents a user of the chatroom system. Attributes include username, email, password, and profile information.
- Chatroom: Represents a chatroom where users can join and participate in conversations. Attributes include chatroom name, description, and list of users.
- Message: Represents a message sent within a chatroom. Attributes include message content, timestamp, and sender.

### User Model

| Endpoints        | URLs           | Description  |
| :-------------: |:-------------:| :-----:|
|GET	| localhost:8080/users	| Retrieve all users|
|GET |	localhost:8080/users/{id}	|Retrieve user by ID|
|POST|	localhost:8080/users	| Create a new user|
|PUT	|localhost:8080/users/{id}	|Update user details|
|DELETE|	localhost:8080/users/{id}|	Delete a user|

### Chatroom Model
Endpoints	URLs	Description
GET	localhost:8080/chatrooms	Retrieve all chatrooms
GET	localhost:8080/chatrooms/{id}	Retrieve chatroom by ID
POST	localhost:8080/chatrooms	Create a new chatroom
PUT	localhost:8080/chatrooms/{id}	Update chatroom details
DELETE	localhost:8080/chatrooms/{id}	Delete a chatroom

### Message Model
Endpoints	URLs	Description
GET	localhost:8080/messages	Retrieve all messages
GET	localhost:8080/messages/{id}	Retrieve message by ID
POST	localhost:8080/messages	Send a new message
DELETE	localhost:8080/messages/{id}	Delete a message

### WebSocket Endpoints
Endpoints	URLs	Description
CONNECT	ws://localhost:8080/chat	Connect to chatroom via WebSocket

