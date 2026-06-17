# Spring OAuth2 Device Flow Monorepo

A lightweight, decoupled Spring Boot monorepo demonstrating the **OAuth2 Device Authorization Grant (Device Flow)**. It showcases how a headless CLI client can securely authenticate a user via a browser login and consume protected API endpoints.

## 🏗️ Architecture

* **`/auth`**: Spring Authorization Server (Port `9090`) - Handles identity management and issues tokens.
* **`/http`**: Spring Resource Server (Port `8085`) - Exposes a secured endpoint (`/rest`) requiring a valid Bearer token.
* **`/shell`**: Spring Shell CLI Client - Triggers the Device Flow, prompts the user with a login URL, polls for authentication, and securely queries the resource server.

---

## 🚀 Quick Start

Ensure you have **Java 17+** installed, then spin up the services in this exact order:

### 1. Start Authorization Server
```bash
cd auth && ./mvnw spring-boot:run
