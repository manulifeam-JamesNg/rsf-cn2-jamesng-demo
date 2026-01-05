# RSF Connect MongoDB - Reactive MongoDB Application

## Exercise 1: Reactive MongoDB Implementation

### Overview
This Spring Boot application demonstrates reactive programming with MongoDB using Spring Data Reactive Repositories.

### Requirements Completed
✅ 1. Created an EDA AKS Spring Boot Project using RSF  
✅ 2. Store document to MongoDB  
✅ 3. Write a function to retrieve document by using the field "name"  

### Dependencies Added
- `spring-boot-starter-data-mongodb-reactive`

### MongoDB Configuration
MongoDB Atlas connection configured in `application.yml`:
```yaml
spring:
  data:
    mongodb:
      uri: mongodb+srv://[connection-string]
      database: default
```

### Implementation Details

#### 1. Doctor Document Model
- Location: `src/main/java/com/manulife/ap/model/Doctor.java`
- Fields: id, name, gender
- Annotated with `@Document(collection = "doctor")`

#### 2. Doctor Repository
- Location: `src/main/java/com/manulife/ap/repository/DoctorRepository.java`
- Extends `ReactiveMongoRepository<Doctor, String>`
- Custom query method: `findDoctorByRegExName(String name)` using regex

#### 3. Two Implementation Approaches

##### A. Annotation Way (Controller)
- Location: `src/main/java/com/manulife/ap/controller/DoctorController.java`
- Endpoints:
  - `GET /doctors` - Retrieve all doctors
  - `GET /doctors/{id}` - Retrieve single doctor by ID
  - `GET /doctors/search/{name}` - Search doctors by name (regex)
  - `POST /doctors` - Create new doctor
  - `PUT /doctors/{id}` - Update doctor
  - `DELETE /doctors/{id}` - Delete single doctor
  - `DELETE /doctors` - Delete all doctors

##### B. Functional Programming Way (Router + Handler)
- Handler: `src/main/java/com/manulife/ap/handler/DoctorHandler.java`
- Router: `src/main/java/com/manulife/ap/router/DoctorRouter.java`
- Endpoints (prefixed with /func):
  - `GET /func/doctors` - Retrieve all doctors
  - `GET /func/doctors/{id}` - Retrieve single doctor
  - `POST /func/doctors` - Create new doctor
  - `PUT /func/doctors/{id}` - Update doctor
  - `DELETE /func/doctors/{id}` - Delete doctor

### Testing the Application

#### 1. Build the Application
```bash
mvn clean package
```

#### 2. Run the Application
```bash
mvn spring-boot:run
```

Or run from IDE by executing the `Application.java` main class.

#### 3. Test Endpoints

**Create a Doctor (POST)**
```bash
curl -X POST http://localhost:8080/doctors \
  -H "Content-Type: application/json" \
  -d '{"name": "Jian", "gender": "M"}'
```

**Get All Doctors (GET)**
```bash
curl http://localhost:8080/doctors
```

**Search Doctor by Name (GET)**
```bash
curl http://localhost:8080/doctors/search/Jian
```

**Get Doctor by ID (GET)**
```bash
curl http://localhost:8080/doctors/{doctorId}
```

**Update Doctor (PUT)**
```bash
curl -X PUT http://localhost:8080/doctors/{doctorId} \
  -H "Content-Type: application/json" \
  -d '{"name": "Jane Updated", "gender": "F"}'
```

**Delete Doctor (DELETE)**
```bash
curl -X DELETE http://localhost:8080/doctors/{doctorId}
```

#### 4. Test Functional Endpoints
Simply replace `/doctors` with `/func/doctors` in the above examples:
```bash
curl http://localhost:8080/func/doctors
```

### Expected Response Format
```json
{
  "id": "607999708a4e54f9265e547",
  "name": "Jian",
  "gender": "M"
}
```

### Notes
- The application uses Server-Sent Events (TEXT_EVENT_STREAM) for streaming responses
- All operations are non-blocking and reactive
- MongoDB Atlas is used as the database backend
- The application runs on port 8080 by default
