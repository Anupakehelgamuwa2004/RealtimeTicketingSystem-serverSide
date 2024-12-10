# Ticket Selling System README

## Overview
This is a multi-threaded ticket selling system built with Spring Boot, featuring concurrent ticket sales with vendors and customers, real-time WebSocket messaging, and configurable parameters.

## System Architecture
The system simulates a ticket selling environment with the following key components:
- Vendors: Add tickets to the pool
- Customers: Purchase tickets from the pool
- Configuration: Control system parameters
- WebSocket: Provide real-time updates

## Prerequisites
- Java 17 or higher
- MySQL Database
- Spring Boot
- Maven or Gradle

## Database Setup
1. Create a MySQL database named `TicketingSystem`
2. Configure database credentials in `application.properties`
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/TicketingSystem
   spring.datasource.username=YOUR_USERNAME
   spring.datasource.password=YOUR_PASSWORD
   ```

## Configuration Parameters
The system allows configuration of:
- Maximum Ticket Capacity
- Total Tickets
- Ticket Release Rate
- Customer Retrieval Rate

## API Endpoints

### Configuration Management
- `POST /config/update`: Update system configuration
  - Validates total tickets do not exceed max capacity
- `GET /config/get`: Retrieve current configuration
- `POST /config/start`: Start ticket selling threads
- `POST /config/stop`: Stop ticket selling threads

### WebSocket Messaging
- Endpoint: `/chat`
- Topics:
  - `/topic/tickets`: Real-time ticket updates
  - `/topic/messages`: Custom messaging

## Running the Application
1. Clone the repository
2. Configure database settings
3. Build the project
   ```bash
   mvn clean install
   ```
4. Run the application
   ```bash
   mvn spring-boot:run
   ```

## Thread Management
- Two vendors add tickets to the pool
- Two customers purchase tickets
- Threads can be started and stopped via API endpoints

## Validation Rules
- Max Ticket Capacity must be > 0
- Total Tickets must be > 0
- Ticket Release Rate must be > 0
- Customer Retrieval Rate must be > 0

## Error Handling
- Comprehensive error handling with `GlobalExceptionHandler`
- Validation errors return detailed messages
- Illegal argument exceptions are caught and processed

## Logging
- Uses Java Logging framework
- Tracks ticket additions, purchases, and thread activities

## Recommended Client-Side Integration
- Use WebSocket client to receive real-time updates
- Implement frontend to interact with configuration endpoints
- Handle configuration and thread management

## Potential Improvements
- Add more vendors/customers
- Implement more sophisticated concurrency controls
- Add persistent logging
- Create more detailed reporting mechanisms

## Troubleshooting
- Ensure MySQL is running
- Check database connection parameters
- Verify network ports (default: 9090)

## Security Considerations
- Add authentication/authorization
- Implement input sanitization
- Use HTTPS for WebSocket connections

## License
[Add your license information here]

## Contributors
[List contributors or maintainers]
