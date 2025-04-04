# SEIP - Assignment 1

# AresCore - A Martian CRUD Application for SpaceX MarsOps.

AresCore is a resource inventory management system for MarsOps space operations. It allows users to manage, track and categorize resources within a space station environment efficiently.

## Technologies Used
- **Maven** - Build Automation & Dependency Management
- **Spring Boot 3.4.4** - Backend Framework
- **Thymeleaf** - Frontend Template Engine
- **H2 Database** - In-memory Database for Lightweight Storage
- **Checkstyle** - Code Quality Enforcement

## Phase 1 - Repository Initialization

### Installation

1. Clone the Repository
```sh
git clone https://github.com/KonstantinosKorovesis/ares-core
cd ares-core
```
2. Build with Maven (use mvnw if Maven is not installed):
```sh
mvn clean package
```
3. Run the Application:
```sh
java -jar target/ares-core-1.0.0.jar
```
4. Access the application at http://localhost:8080/resources

## Phase 2 - Unit Tests + CI Workflow

- **JUnit** - Tests the Application Controller and Repository
- **GitHub Action** - main.yml CI Workflow that runs Unit, Code Quality and Integration Tests on Code Push and Pull Requests
- **Main Branch Protection** - Only merge changes into the main branch after passing all checks