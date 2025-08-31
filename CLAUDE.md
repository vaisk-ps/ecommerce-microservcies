# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build and Development Commands

### Build System
- **Build entire platform:** `./mvnw clean package`
- **Run individual service:** `./mvnw spring-boot:run -pl <platform-service>`
- **Execute test suite:** `./mvnw test`
- **Test specific service:** `./mvnw test -pl <platform-service>`

### Container Operations
- **Launch platform:** `docker-compose -f compose.yml up`
- **Background deployment:** `docker-compose -f compose.yml up -d`
- **Shutdown platform:** `docker-compose -f compose.yml down --remove-orphans`
- **Monitor logs:** `docker-compose -f compose.yml logs -f <platform-service>`

## Platform Architecture

### Distributed Services Framework
This is a Spring Cloud-based distributed platform with these infrastructure components:

- **service-registry** (Port 8761): Eureka service discovery
- **configuration-server** (Port 9296): Centralized config management
- **api-gateway** (Port 8080): Edge service and routing
- **auth-service** (Port 8900): Identity and access management

### Core Business Services
- **user-management** (Port 8700): Platform user profiles and credentials
- **product-catalog** (Port 8500): Product information and categorization
- **order-management** (Port 8300): Order processing and cart management
- **payment-processor** (Port 8400): Transaction and payment handling
- **logistics-service** (Port 8600): Shipping and fulfillment operations
- **wishlist-service** (Port 8800): Customer preference management

### Observability Infrastructure
- **Distributed Tracing** (Port 9411): Zipkin-based request tracing
- **Health Monitoring**: Spring Actuator endpoints at `/actuator/health`
- **Metrics Collection**: Prometheus integration at `/actuator/prometheus`
- **Resilience Patterns**: Resilience4j circuit breakers and timeouts

## Technology Stack

- **Java 11**: Primary language version
- **Spring Boot 2.5.7**: Application framework
- **Spring Cloud 2020.0.4**: Microservices patterns
- **H2/MySQL**: Database options (check individual service configs)
- **Docker**: Containerization
- **Maven**: Build tool
- **Testcontainers 1.16.0**: Integration testing

## Development Patterns

### Package Architecture
All platform services follow this structure:
```
com.marketplace.platform.[service]/
├── core/
│   ├── entity/      # JPA domain entities
│   ├── dto/         # Data transfer objects
│   ├── service/     # Business logic layer
│   └── repository/  # Data access layer
├── web/
│   └── controller/  # REST API endpoints
├── config/          # Service configuration
├── exception/       # Error handling
└── util/            # Helper utilities
```

### Shared Components
- **AbstractMappedEntity**: Base entity with audit fields
- **GlobalExceptionHandler**: Centralized error handling
- **PlatformConstants**: Service-wide constants
- **EntityMapper interfaces**: Entity-DTO transformation

### Service Communication
- Services communicate through Eureka service discovery
- REST endpoints are exposed via API Gateway
- Circuit breakers implemented for fault tolerance

## API Access Points

- **Main API Gateway**: https://localhost:8080
- **Proxy Client Swagger**: https://localhost:8900/swagger-ui.html
- **User Service Swagger**: https://localhost:8700/swagger-ui.html
- **Eureka Dashboard**: http://localhost:8761/eureka
- **Zipkin Tracing**: http://localhost:9411/zipkin/

## Configuration Notes

- All services use `SPRING_PROFILES_ACTIVE=dev` in Docker
- Health checks available at `/actuator/health` for each service
- Prometheus metrics exposed at `/actuator/prometheus`
- Swagger documentation available for main services

## Database Architecture

Each service manages its own database (database-per-service pattern):
- User data in user-service
- Product catalog in product-service  
- Orders in order-service
- Payments in payment-service
- Shipping in shipping-service
- Favorites in favourite-service

## Testing Strategy

- Unit tests with JUnit 5
- Integration tests with Testcontainers
- Service health verification through actuator endpoints
- End-to-end testing through API Gateway