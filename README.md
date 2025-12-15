# Smart Travel Booking Platform - Microservices Assignment

##  Assignment Overview
**University:** University of Sri Jayewardenepura  
**Course:** ITS 4243 - Microservices and Cloud Computing  
**Assignment:** 02 - Smart Travel Booking Platform  
**Objective:** Build a distributed travel booking backend with 6 microservices using REST API, Feign Client, and WebClient

---

##  Architecture Diagram
┌─────────────────────────────────────────────────────────┐
│ SMART TRAVEL PLATFORM │
│ Microservices Architecture │
├─────────────────────────────────────────────────────────┤
│ │
│ ┌─────────────────────────────────────────────────┐ │
│ │ Booking Service (8081) │ │
│ │ (Main Orchestrator) │ │
│ └──────┬──────────────┬──────────────┬────────────┘ │
│ │ │ │ │
│ ┌──────▼─────┐ ┌──────▼─────┐ ┌──────▼─────┐ │
│ │ User │ │ Flight │ │ Hotel │ │
│ │ Service │ │ Service │ │ Service │ │
│ │ (8086) │ │ (8082) │ │ (8083) │ │
│ │ [WebClient]│ │ [Feign] │ │ [Feign] │ │
│ └────────────┘ └────────────┘ └────────────┘ │
│ │ │ │ │
│ ┌──────▼──────────────▼──────────────▼─────┐ │
│ │ Notification Service │ │
│ │ (8084) │ │
│ │ [WebClient] │ │
│ └──────────────────────────────────────────┘ │
│ │ │
│ ┌──────▼─────┐ │
│ │ Payment │ │
│ │ Service │ │
│ │ (8085) │ │
│ │ [WebClient]│ │
│ └────────────┘ │
│ │
└─────────────────────────────────────────────────────────┘


---

## Microservices Overview

| Service | Port | Communication Method | Purpose |
|---------|------|----------------------|---------|
| **User Service** | 8086 | WebClient (GET) | User authentication and validation |
| **Flight Service** | 8082 | Feign Client (GET) | Flight availability and pricing |
| **Hotel Service** | 8083 | Feign Client (GET) | Hotel availability and pricing |
| **Booking Service** | 8081 | Orchestrator | Main booking flow coordination |
| **Payment Service** | 8085 | WebClient (POST) | Payment processing |
| **Notification Service** | 8084 | WebClient (POST) | Sending notifications |

---

## Communication Flow

### 1. **Booking Flow Sequence**
User → Booking Service (POST /bookings)

Booking Service → User Service (WebClient - Validate user)

Booking Service → Flight Service (Feign Client - Check availability)

Booking Service → Hotel Service (Feign Client - Check availability)

Booking Service → Payment Service (WebClient - Process payment)

Payment Service → Booking Service (WebClient - Confirm booking)

Booking Service → Notification Service (WebClient - Send confirmation)

### 2. **API Endpoints**
```yaml
# User Service
GET   http://localhost:8086/users/{id}

# Flight Service  
GET   http://localhost:8082/flights/{id}

# Hotel Service
GET   http://localhost:8083/hotels/{id}

# Booking Service
POST  http://localhost:8081/bookings
POST  http://localhost:8081/bookings/confirm/{id}

# Payment Service
POST  http://localhost:8085/payments

# Notification Service
POST  http://localhost:8084/notifications


