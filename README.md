# RealtimeTicketingSystem
A Spring Boot and Angular-based multi-threaded ticketing system with real-time WebSocket updates and MySQL-backed configuration management.


# CLI Ticketing System

A comprehensive multi-threaded ticketing system built with Spring Boot that simulates ticket management. The system allows vendors to add tickets and customers to purchase them through RESTful APIs and real-time updates via WebSockets. Configuration settings are managed through a MySQL database, ensuring persistence and flexibility.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [WebSocket Integration](#websocket-integration)
- [Technologies Used](#technologies-used)
- [Logging](#logging)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Ticket Pool Management**: Vendors can add tickets to the pool, and customers can purchase them concurrently.
- **Multiple Vendors and Customers**: Supports multiple vendor and customer threads for simultaneous operations.
- **Persistent Configuration**: Configuration settings are stored and managed in a MySQL database.
- **Real-Time Updates**: Utilizes WebSockets to provide real-time updates on ticket transactions.
- **RESTful APIs**: Manage configurations and control the ticketing process via REST endpoints.
- **Thread Safety**: Ensures safe concurrent access to the ticket pool using synchronized methods.
- **Graceful Shutdown**: Allows stopping of ticket selling and purchasing processes without data loss.

## Prerequisites

- **Java**: JDK 11 or higher
- **Maven**: For building the project
- **MySQL**: For the database
- **Git**: For cloning the repository

## Installation






# CLI Ticketing System

A comprehensive multi-threaded ticketing system built with Spring Boot and Angular that simulates ticket management. The system allows vendors to add tickets and customers to purchase them through RESTful APIs and real-time updates via WebSockets. Configuration settings are managed through a MySQL database, ensuring persistence and flexibility.


## Features

- **Ticket Pool Management**: Vendors can add tickets to the pool, and customers can purchase them concurrently.
- **Multiple Vendors and Customers**: Supports multiple vendor and customer threads for simultaneous operations.
- **Persistent Configuration**: Configuration settings are stored and managed in a MySQL database.
- **Real-Time Updates**: Utilizes WebSockets to provide real-time updates on ticket transactions.
- **RESTful APIs**: Manage configurations and control the ticketing process via REST endpoints.
- **Thread Safety**: Ensures safe concurrent access to the ticket pool using synchronized methods.
- **Graceful Shutdown**: Allows stopping of ticket selling and purchasing processes without data loss.

## Technologies Used

- **Backend**:
  - Java 11
  - Spring Boot
  - Spring WebSocket
  - Spring Data JPA
  - MySQL
  - Gson
  - Maven

- **Frontend**:
  - Angular
  - TypeScript
  - HTML/CSS
  - SockJS and StompJS for WebSockets

## Prerequisites

- **Java**: JDK 11 or higher
- **Maven**: For building the backend
- **Node.js**: For running the frontend
- **Angular CLI**: For building the frontend
- **MySQL**: For the database
- **Git**: For cloning the repository

## Installation




