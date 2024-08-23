# Chyme - Chatroom

## Summary

Chyme is an application for a chatroom system designed to facilitate real-time communication among users. It supports user registration, authentication and chat management features. Users can create and join chatrooms, send and receive messages and manage their profiles. The system is optimised for handling multiple users and ensuring secure communication.

## Frontend Tech Stack
- **React Typescript**

## Backend Tech Stack

- **Java**
- **Spring Boot**
- **PostgreSQL**

## Technologies Used
- **JsonWebToken**
- **WebSocket** (IN PROGRESS)
- **Docker** (IN PROGRESS)

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

- User: Represents a user of the chatroom system. Attributes include username, email and password.
- Room: Represents a chatroom where users can join and participate in conversations. Attributes include chatroom name and list of users.
- Message: Represents a message sent within a chatroom. Attributes include message content, timestamp and sender.

### User

| Endpoints        | URLs           | Description  |
| :-------------: |:-------------:| :-----:|
~~|GET	| localhost:8080/users	| Retrieve all users|~~
|GET |	localhost:8080/users/{id}	|Retrieve user by ID|
|POST|	localhost:8080/users	| Create a new user|
|PATCH	|localhost:8080/users/{id}/username	|Update user details|
|DELETE|	localhost:8080/users/{id}|	Delete a user|

### Message

| Endpoints        | URLs           | Description  |
| :-------------: |:-------------:| :-----:|
|GET |	localhost:8080/messages/{id}	|Retrieve message by ID|
|GET |	localhost:8080/messages/room/{id}	|Retrieve all messages in a room by ID|
|POST|	localhost:8080/messages	| Create a new message|

### Room

| Endpoints        | URLs           | Description  |
| :-------------: |:-------------:| :-----:|
~~|GET	| localhost:8080/rooms	| Retrieve all rooms|~~
|GET |	localhost:8080/rooms/{id}	|Retrieve room by ID|
|GET |	localhost:8080/rooms/{id}/users	|Retrieve all users in a room by ID|
|PATCH	|localhost:8080/rooms/{id}	|Update room details|
|DELETE|	localhost:8080/rooms/{id}|	Delete a room|

### UserRoom

| Endpoints        | URLs           | Description  |
| :-------------: |:-------------:| :-----:|
|Delete	| localhost:8080/user-room/{userId}/rooms/{roomId}	| Delete a user from a room|



