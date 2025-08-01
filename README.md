# Spring Boot - REST & SOAP Microservices Project

This repository contains one of two Spring Boot microservices designed to showcase internal communication using SOAP and external access through RESTful APIs.

## 🧩 Services Overview

### 🔹 Profile Service
- Create and retrieve user profiles (bio, location, age)
- Before saving a profile, validates user existence by calling the `user-service` via SOAP
- Exposes REST endpoints to manage profiles
- Combines profile + user data in responses for enriched results

🔗 GitHub: [profile-service](https://github.com/VahidMousavii/profile-service)

---

## ⚙️ Technologies Used

- Java 21
- Spring Boot 3
- Spring Web (REST)
- Spring-WS (SOAP Client)
- MapStruct
- Lombok
- PostgreSQL
- Maven
- Swagger/OpenAPI
- Clean Architecture principles
- SoC (Separation of Concerns)

---

## 🚀 Running the Services

I have deployed both services on a VPS and successfully launched the JAR files on the server.  
PostgreSQL has also been installed and properly configured for database persistence.

You can access the Swagger UI of the project here:  
🔗 http://37.32.26.21:9091/swagger-ui/index.html

Although I initially planned to dockerize the services for production, I wasn’t able to complete it due to time constraints.

## 🚀 Running the Project Locally
However, if you'd like to run the project locally, it can easily be done via IntelliJ IDEA or the terminal using the following Maven command:  "mvn spring-boot:run"
Also, for convenience, I’ve configured the PostgreSQL to accept all incoming connections — so you shouldn’t encounter any database access issues when running on **PROD** environment **locally**.


### 📦 Prerequisites
- Java 21+ : https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html
- Maven 3.8+  : https://maven.apache.org/download.cgi
- PostgreSQL  : https://www.postgresql.org/download/
- -Soap-UI for SOAP Test: https://www.soapui.org/downloads/soapui/
- IntelliJ IDEA : https://www.jetbrains.com/idea/download/

I appreciate the chance to demonstrate my skills through this project.
Thank you for your consideration.
