# Ticketing System

## Overview
This is a Ticketing System that simulates the selling and purchasing of tickets. It includes a set of vendors adding tickets to a pool and customers purchasing tickets from the pool. The system uses Spring Boot for the backend, with WebSocket support for real-time updates, and MySQL for storing configurations. The application also provides a CLI interface for ticket management and a REST API to interact with the configuration.

## Prerequisites
Before running the system, ensure you have the following:
- **Java JDK** (version 8 or higher) installed on your system.
- **Maven** for dependency management and build.
- **MySQL** installed and running.
- **Spring Boot** to manage the application.
- **WebSocket Support** in your front-end application for real-time messaging.

## Setup

1. **Clone or Download the Project:**
   - Clone this repository or download the source files to your local system.

2. **Database Setup (MySQL):**
   - Create a MySQL database for the system, for example:
     ```sql
     CREATE DATABASE TicketingSystem;
     ```
   - Ensure you have a table that can store the configuration data.

3. **Configure Database Connection:**
   - In the `application.properties` file, make sure the following properties match your MySQL setup:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/TicketingSystem
     spring.datasource.username=root
     spring.datasource.password=1234
     spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
     server.port=9090
     ```

4. **Install Dependencies:**
   - If you are using Maven, run the following command to install the required dependencies:
     ```bash
     mvn clean install
     ```

5. **Run the Application:**
   - After installing dependencies, run the application with:
     ```bash
     mvn spring-boot:run
     ```
   - The server will start on `http://localhost:9090`.

## Configuration Management
The configuration for the system (max ticket capacity, total tickets, ticket release rate, and customer retrieval rate) is managed both in the database and via the `ConfigurationController` REST API.

1. **Default Configuration:**
   - If the configuration is not already set in the database, the system will automatically initialize it with default values:
     - Max Ticket Capacity: 100
     - Total Tickets: 50
     - Ticket Release Rate: 1000 ms
     - Customer Retrieval Rate: 1000 ms

2. **REST API Endpoints:**
   - **Update Configuration** (`POST /config/update`):
     - Updates the configuration values. Send a JSON body with the new values.
     - Example request:
       ```json
       {
         "maxTicketCapacity": 200,
         "totalTickets": 150,
         "ticketReleaseRate": 1000,
         "customerRetrievalRate": 1000
       }
       ```

   - **Get Current Configuration** (`GET /config/get`):
     - Retrieves the current configuration values.

   - **Start Selling and Buying Threads** (`POST /config/start`):
     - Starts the ticket selling and buying process using the current configuration. This will spawn threads for vendors and customers.

   - **Stop Selling and Buying Threads** (`POST /config/stop`):
     - Stops the ticket selling and buying process, terminating all related threads.

3. **CLI Configuration Input:**
   - When the system is run for the first time or if the configuration is missing, it will prompt you to enter values for:
     - Max Ticket Capacity
     - Total Tickets
     - Ticket Release Rate (ms)
     - Customer Retrieval Rate (ms)
   - The system will validate the inputs and store them in the database for future use.

## Ticket Selling and Purchasing Process

1. **Vendors:**
   - Two vendors will add tickets to the pool concurrently.
   - Vendors add tickets in batches (10 tickets at a time).
   - The tickets are added at the rate specified in the configuration (ticket release rate).

2. **Customers:**
   - Two customers will try to purchase tickets concurrently.
   - Customers retrieve tickets at the rate specified in the configuration (customer retrieval rate).
   - The system stops selling tickets when all the tickets are sold.

3. **Real-Time Updates (WebSocket):**
   - The system uses WebSockets to notify the front-end about the ticket status in real-time.
   - Whenever a vendor adds tickets or a customer purchases a ticket, the status is broadcasted to the front-end using WebSockets.

## Running the System

1. **Start the Application:**
   - Run the Spring Boot application using:
     ```bash
     mvn spring-boot:run
     ```

2. **Access the Front-End:**
   - Ensure that your front-end application (e.g., Angular or React) is configured to communicate with the WebSocket endpoint.
   - The WebSocket URL is `/chat` and can be used to receive updates on ticket availability.

3. **Interact via REST API:**
   - Use tools like **Postman** or **curl** to interact with the REST API endpoints for configuration management.

4. **Stopping the Process:**
   - You can stop the process via the REST API (`POST /config/stop`) or through the CLI if you're using a manual intervention approach.

## Example Workflow:

1. Start the application by running it through Spring Boot.
2. Access the configuration endpoints:
   - Set configuration values for ticket pool capacity, release rate, and retrieval rate.
3. Start the ticket selling and purchasing threads using the `/start` endpoint.
4. Monitor the status via the WebSocket endpoint for real-time updates on ticket availability.
5. If you want to stop the process, use the `/stop` endpoint or terminate the application manually.

## Troubleshooting

- **Invalid Inputs:** If invalid values are entered for configuration, the system will ask you to re-enter valid values.
- **Database Connection Issues:** Ensure your MySQL database is running and the credentials in the `application.properties` file are correct.
- **WebSocket Connection Issues:** Ensure the front-end is properly connected to the WebSocket server. You may need to allow WebSocket connections from specific origins in the configuration.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


# Ticketing System - Frontend Setup and Usage Guide

## Overview
This is the frontend application for a Ticketing System that allows vendors to add tickets to a pool, and customers to purchase tickets from that pool. The application is built using Angular and communicates with a backend server to manage ticket configuration and ticket selling/buying processes in real-time. The frontend uses WebSocket to get real-time updates about the ticket status.

The system is composed of several components:
- **BaseComponent**: This component manages the layout and includes other components like `ConfigPageComponent`, `StartStopComponent`, and `TotalTicketComponent`.
- **ConfigPageComponent**: A form for configuring the ticket pool settings such as max capacity, total tickets, and release/retrieval rates.
- **StartStopComponent**: Allows the user to start or stop the ticket selling and buying process.
- **TotalTicketComponent**: Displays the real-time status of the tickets in the system.

## Prerequisites
Before running the system, ensure you have the following:
- **Node.js** installed on your system. You can download it from [here](https://nodejs.org/).
- **Angular CLI** installed. If not, install it globally by running the following command:
  ```bash
  npm install -g @angular/cli
