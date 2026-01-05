# Testing Guide for rsf-connect-mongodb

## Issue: MongoDB Atlas Connection Timeout

Your application cannot connect to MongoDB Atlas. The error shows:
```
Timed out after 30000 ms while waiting for a server
Failed looking up SRV record for '_mongodb._tcp.mua-mongo-db.n0w5a.azure.mongodb.net'
```

## Solutions:

### Option 1: Check MongoDB Atlas Access (Recommended)
1. Verify the MongoDB Atlas cluster is running
2. Check if your IP address is whitelisted in MongoDB Atlas Network Access
3. Verify the connection string credentials are correct

### Option 2: Use Local MongoDB
Install MongoDB locally and change `application.yml`:
```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/default
      database: default
```

### Option 3: Test with Docker MongoDB
```powershell
docker run -d -p 27017:27017 --name mongodb mongo:latest
```

Then update `application.yml` to use `mongodb://localhost:27017/default`

## Testing Commands (PowerShell)

### GET all doctors:
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/doctors" -Method Get
```

### POST create doctor:
```powershell
$body = @{
    name = "Dr. Smith"
    gender = "Male"
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8080/doctors" -Method Post -ContentType "application/json" -Body $body
```

### GET doctor by ID:
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/doctors/{id}" -Method Get
```

### PUT update doctor:
```powershell
$body = @{
    name = "Dr. Updated"
    gender = "Female"
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8080/doctors/{id}" -Method Put -ContentType "application/json" -Body $body
```

### DELETE doctor:
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/doctors/{id}" -Method Delete
```

### DELETE all doctors:
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/doctors" -Method Delete
```

## Alternative: Use curl.exe (not PowerShell alias)

```powershell
curl.exe -X POST http://localhost:8080/doctors -H "Content-Type: application/json" -d "{\"name\":\"Dr. Smith\",\"gender\":\"Male\"}"
```

## Swagger UI
Once MongoDB is connected, access Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```
