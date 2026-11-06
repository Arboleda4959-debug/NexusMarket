# NexusMarket Architecture

## Overview

NexusMarket is organized around Domain-Driven Design (DDD) and Hexagonal Architecture principles.

The goal is to separate business logic from infrastructure and external technologies.

## Main Layers

### Domain

Contains the core business logic:

- Models
- Value Objects
- Domain Services
- Domain Exceptions
- Ports

### Adapters

Responsible for connecting the application with external systems.

### Infrastructure

Contains technical implementations and infrastructure concerns.

## Architectural Principles

- Separation of Concerns
- Dependency Inversion
- Encapsulation of Business Rules
- Domain Independence

## Project Evolution

This architecture is being developed incrementally as part of the NexusMarket project.
