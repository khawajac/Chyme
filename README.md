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

### User Authentication/Registration

| Endpoints        | URLs           | Description  |
| :-------------: |:-------------:| :-----:|
|POST | localhost:8080/auth/authenticate|Authenticate user sign in|
|POST	|localhost:8080/auth/register	|A new user can register and be created|




