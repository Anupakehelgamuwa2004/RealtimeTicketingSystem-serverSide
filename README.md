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




# Ticket Management System

## Overview
This is a full-stack Ticket Management System built with Angular (Frontend) and a backend service. The application allows users to configure, start, stop, and monitor a ticket selling and buying process.

## Prerequisites
- Node.js (version 16 or higher)
- Angular CLI
- Java Development Kit (JDK) 11 or higher
- Maven or Gradle

## Frontend Setup

### Installation
1. Clone the repository
2. Navigate to the frontend directory
3. Install dependencies:
```bash
npm install
```

### Dependencies
Key dependencies include:
- Angular
- RxJS
- SockJS
- StompJS
- HttpClientModule

### Configuration
- Ensure backend server is running on `http://localhost:9090`
- WebSocket connection endpoint: `http://localhost:9090/chat`

## Features

### Configuration Page
- Set maximum ticket capacity
- Configure total tickets
- Define ticket release and retrieval rates
- Load previous configurations

### Start/Stop Controls
- Start and stop ticket selling/buying threads
- Fetch current configuration

### Real-time Ticket Monitoring
- WebSocket integration for live updates
- Display ticket addition and purchase events

## Running the Application

### Frontend
```bash
ng serve
```
- Application will be available at `http://localhost:4200`

### Backend
- Ensure your Spring Boot backend is running on port 9090
- Required endpoints:
  - `/config/update` (POST): Update configuration
  - `/config/get` (GET): Retrieve current configuration
  - `/config/start` (POST): Start ticket threads
  - `/config/stop` (POST): Stop ticket threads

## Endpoints and API Interactions

### Configuration Update
- **Endpoint**: `http://localhost:9090/config/update`
- **Method**: POST
- **Payload**: 
```json
{
  "maxTicketCapacity": 100,
  "totalTickets": 50,
  "ticketReleaseRate": 1000,
  "customerRetrievalRate": 1000
}
```

### Start/Stop Threads
- **Start Endpoint**: `http://localhost:9090/config/start`
- **Stop Endpoint**: `http://localhost:9090/config/stop`

## WebSocket Communication
- Subscribes to `/topic/tickets`
- Receives real-time updates about:
  - Ticket additions
  - Ticket purchases

## Troubleshooting

### Common Issues
1. **Connection Refused**
   - Verify backend server is running
   - Check CORS settings
   - Confirm port configuration

2. **WebSocket Connection Failure**
   - Ensure SockJS and StompJS are correctly imported
   - Verify WebSocket endpoint URL

### Logging
- Frontend: Check browser console
- Backend: Review server logs

## Development Notes

### Component Structure
- `BaseComponent`: Main container
- `ConfigPageComponent`: Configuration settings
- `StartStopComponent`: Control thread execution
- `TotalTicketComponent`: Real-time ticket monitoring

### Styling
- Responsive design
- Custom CSS with flexibility
- Gradient and hover effects

## Contributing
1. Fork the repository
2. Create a feature branch
3. Commit changes
4. Push to the branch
5. Create a Pull Request

## License
[Specify your license here]

## Contact
[Your contact information or support email]
