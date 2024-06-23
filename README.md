# FoodCartMicroservice
This project is built using a microservices architecture. The goal is to create a scalable, maintainable, and easily deployable application that handles various aspects of an Food Cart system. The architecture utilizes Core Java, Spring Boot, Spring Cloud, Netflix Eureka, Spring Cloud Gateway, OpenFiegn and other technologies to achieve this.

Services
Product Service

Manages product data, including creation, retrieval, update, and deletion of product information.
Endpoint: /products/**
Port: 8081

User Service

Manages user data, including registration, retrieval, update, and deletion, and profile management.
Endpoint: /users/**
Port: 8082

Cart Service

Manages shopping cart data, including adding items to the cart, viewing cart contents, and removing items from the cart.
Endpoint: /carts/**
Port: 8083

API Gateway

Acts as a single entry point for all client requests, routing them to the appropriate microservice.
Implements load balancing, routing, and filtering.
Endpoint: /
Port: 8184

Eureka Server

Service discovery server that keeps track of all registered microservices and their instances.
Port: 8761


# Architecture
Spring Boot: Provides the foundation for building microservices.
Spring Cloud: Adds support for distributed systems.
Netflix Eureka: Handles service discovery.
Spring Cloud Gateway: Acts as an API Gateway for routing and load balancing.
Feign Client: Simplifies service-to-service calls.

# Prerequisites
JDK 17 or higher
Maven 3.8.1 or higher
STS tool
