# RSF CN2 James Demo - Local Testing Guide

## Quick Start Commands

### 1. Start MongoDB Server
```powershell
Start-Process -FilePath "C:\Code Folder\MongoDB\MongoDBPortable-0.5.0.2\MongoDBPortable\App\MongoDB\bin\mongod.exe" -ArgumentList "--dbpath `"C:\Code Folder\MongoDB\MongoDBPortable-0.5.0.2\MongoDBPortable\Data\DB`" --port 27017" -WindowStyle Minimized
```

### 2. Run Application
```powershell
cd "C:\Code Folder\rsf-cn2-jamesng-demo"
mvn spring-boot:run
```

## Test Endpoints

### Get All Doctors
```powershell
# Browser
http://localhost:8080/doctors

# PowerShell
Invoke-RestMethod -Uri "http://localhost:8080/doctors"
```

### Get Doctor by ID
```powershell
# Browser
http://localhost:8080/doctors/{doctorId}

# PowerShell
Invoke-RestMethod -Uri "http://localhost:8080/doctors/65d1f908nn1bhf401837892hj"
```

### Create Doctor
```powershell
$body = '{"name":"Dr. Smith","gender":"Male"}'
Invoke-RestMethod -Uri "http://localhost:8080/doctors" -Method Post -ContentType "application/json" -Body $body
```

### Update Doctor
```powershell
$updateBody = '{"name":"Dr. Smith Updated","gender":"Male"}'
Invoke-RestMethod -Uri "http://localhost:8080/doctors/65d1f908nn1bhf401837892hj" -Method Put -ContentType "application/json" -Body $updateBody
```

### Delete Doctor
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/doctors/65d1f908nn1bhf401837892hj" -Method Delete
```

## Automated Test Script

```powershell
# Test all endpoints automatically
cd "C:\Code Folder\rsf-cn2-jamesng-demo"
.\test-local-api.ps1
```
