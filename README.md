📚 Training & Course Management API

# Technology Stack
Java 21
Spring Boot 3.x.x
Spring Data JPA
Hibernate
MySQL Database
Lombok
Swagger 3 (Springfox)
Maven

# Features
CRUD operations for Course and TrainingCenter
Search by Center Name and Course Name
Advanced search by Course Name with flexible queries
Swagger API Documentation
Global Exception Handling for consistent error responses


⚙️ Setup Instructions
1. Clone the Repository
2. Configure MySQL Database
3. Run MySQL Server
4.  Build and Run the Application


📝 API Documentation with Swagger
Swagger UI is available after running the application:

URL: http://localhost:8080/swagger-ui/index.html


## 📚 API Endpoints

### 🎯 Course API

| Method   | Endpoint                     | Description           |
|----------|------------------------------|-----------------------|
| `POST`   | `/api/v1/courses`            | Create a new course   |
| `GET`    | `/api/v1/courses`            | Get all courses       |
| `PUT`    | `/api/v1/courses/{courseId}` | Update a course by ID |
| `DELETE` | `/api/v1/courses/{courseId}` | Delete a course by ID |



### 🎯 Training Center API

| Method   | Endpoint                                                           | Description                       |
|----------|--------------------------------------------------------------------|-----------------------------------|
| `POST`   | `/api/v1/training-centers`                                         | Create a new training center      |
| `GET`    | `/api/v1/training-centers`                                         | Get all training centers          |
| `PUT`    | `/api/v1/training-centers/{trainingCenterId}`                      | Update a training center by ID    |
| `DELETE` | `/api/v1/training-centers/{trainingCenterId}`                      | Delete a training center by ID    |
| `GET`    | `/api/v1/training-centers/searchByCenterName?centerName={name}`    | Search by training center name    |
| `GET`    | `/api/v1/training-centers/searchByCourseName?courseName={name}`    | Search by course name             |
| `GET`    | `/api/v1/training-centers/advancedSearchByCourseName?query={name}` | Advanced search -- you can search |
                                                                 institute by giving some letter of your institute  |


⚡ Global Exception Handling
A GlobalExceptionHandler is implemented to handle exceptions consistently across the API.
