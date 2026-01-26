# Local MongoDB CRUD API Test Script

$baseUrl = "http://localhost:8080"

Write-Host "`n========================================" -ForegroundColor Cyan
Write-Host "   Testing Local MongoDB CRUD API" -ForegroundColor Green
Write-Host "========================================`n" -ForegroundColor Cyan

# Test 1: Get all doctors
Write-Host "1. Getting All Doctors..." -ForegroundColor Yellow
try {
    $doctors = Invoke-RestMethod -Uri "$baseUrl/doctors"
    Write-Host "   ✓ Found $($doctors.Count) doctors" -ForegroundColor Green
    $doctors | ForEach-Object { Write-Host "     - $($_.name) ($($_.gender))" -ForegroundColor White }
} catch {
    Write-Host "   ✗ Failed: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# Test 2: Create new doctor
Write-Host "2. Creating New Doctor..." -ForegroundColor Yellow
try {
    $body = '{"name":"Dr. James Smith","gender":"Male"}'
    $newDoctor = Invoke-RestMethod -Uri "$baseUrl/doctors" -Method Post -ContentType "application/json" -Body $body
    Write-Host "   ✓ Created: $($newDoctor.name) (ID: $($newDoctor.id))" -ForegroundColor Green
    $doctorId = $newDoctor.id
} catch {
    Write-Host "   ✗ Failed: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# Test 3: Get specific doctor
if ($doctorId) {
    Write-Host "3. Getting Doctor by ID..." -ForegroundColor Yellow
    try {
        $doctor = Invoke-RestMethod -Uri "$baseUrl/doctors/$doctorId"
        Write-Host "   ✓ Found: $($doctor.name) - $($doctor.gender)" -ForegroundColor Green
    } catch {
        Write-Host "   ✗ Failed: $($_.Exception.Message)" -ForegroundColor Red
    }
    Write-Host ""

    # Test 4: Update doctor
    Write-Host "4. Updating Doctor..." -ForegroundColor Yellow
    try {
        $updateBody = '{"name":"Dr. James Smith Updated","gender":"Male"}'
        $updated = Invoke-RestMethod -Uri "$baseUrl/doctors/$doctorId" -Method Put -ContentType "application/json" -Body $updateBody
        Write-Host "   ✓ Updated: $($updated.name)" -ForegroundColor Green
    } catch {
        Write-Host "   ✗ Failed: $($_.Exception.Message)" -ForegroundColor Red
    }
    Write-Host ""

    # Test 5: Delete doctor
    Write-Host "5. Deleting Doctor..." -ForegroundColor Yellow
    try {
        Invoke-RestMethod -Uri "$baseUrl/doctors/$doctorId" -Method Delete
        Write-Host "   ✓ Deleted successfully" -ForegroundColor Green
    } catch {
        Write-Host "   ✗ Failed: $($_.Exception.Message)" -ForegroundColor Red
    }
    Write-Host ""
}

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "   Testing Complete!" -ForegroundColor Green
Write-Host "========================================`n" -ForegroundColor Cyan

Write-Host "To view all doctors, visit:" -ForegroundColor Yellow
Write-Host "  http://localhost:8080/doctors`n" -ForegroundColor Cyan
