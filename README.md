# Task List App - Backend

Welcome to the Task List App backend! This Spring application provides the server-side functionality for managing tasks.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Docker Setup](#docker-setup)
  - [Running the Application](#running-the-application)
- [Configuration](#configuration)
- [Endpoints](#endpoints)
- [License](#license)

## Prerequisites

Before you begin, ensure you have the following prerequisites installed:

- [Docker](https://www.docker.com/get-started)

## Getting Started

### Docker Setup

### 1. **Build the Docker Image:**
   Navigate to the `task_list_app/task_list_app/database` directory and run:

   docker build -t my_task_image .

### 2. **Running the Application:**
    Run the Docker Container:
    Replace /path/to/host with the absolute path to your desired host directory.

    docker run -d -p 5432:5432 -v /path/to/host:/var/lib/postgresql/data --name my_task_container my_task_image
    This command runs the Docker container with the necessary configurations, including mounting the local volume.

### 3.  **Access the Application:**

    The application will be accessible at http://localhost:8080.
## 4.  **Configuration:**

    Database Configuration
    The application uses PostgreSQL as its database.
    Database configuration properties are specified in the application.properties file.
    Endpoints
        Task Endpoints:
            Retrieve all tasks: GET /tasks
            Create a new task: POST /tasks
            Retrieve a specific task: GET /tasks/{taskId}
            Update a task: PUT /tasks/{taskId}
            Delete a task: DELETE /tasks/{taskId}

**License**
This project is licensed under the MIT License - see the LICENSE file for details.   
