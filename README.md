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
