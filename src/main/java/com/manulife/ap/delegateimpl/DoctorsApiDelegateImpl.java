package com.manulife.ap.delegateimpl;

import com.manulife.ap.model.Doctor;
import com.manulife.ap.repository.DoctorRepository;
import com.manulife.ap.rsf_connect_mongodb.swagger.api.DoctorsApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * DoctorsApiDelegateImpl - Implements the Swagger-generated DoctorsApiDelegate interface
 * This connects the Swagger /doctors API with the MongoDB repository
 */
@Component
public class DoctorsApiDelegateImpl implements DoctorsApiDelegate {
    
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public ResponseEntity<List<com.manulife.ap.rsf_connect_mongodb.swagger.model.Doctor>> getAllDoctors() {
        List<com.manulife.ap.rsf_connect_mongodb.swagger.model.Doctor> doctors = doctorRepository.findAll()
                .map(this::convertToSwaggerDoctor)
                .collectList()
                .block();
        
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteDoctor(String id) {
        doctorRepository.deleteById(id).block();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<com.manulife.ap.rsf_connect_mongodb.swagger.model.Doctor> getDoctorById(String id) {
        Doctor doctor = doctorRepository.findById(id).block();
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(convertToSwaggerDoctor(doctor), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<com.manulife.ap.rsf_connect_mongodb.swagger.model.Doctor> updateDoctor(String id, com.manulife.ap.rsf_connect_mongodb.swagger.model.Doctor swaggerDoctor) {
        Doctor existingDoctor = doctorRepository.findById(id).block();
        if (existingDoctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        existingDoctor.setName(swaggerDoctor.getName());
        existingDoctor.setGender(swaggerDoctor.getGender());
        
        Doctor updatedDoctor = doctorRepository.save(existingDoctor).block();
        return new ResponseEntity<>(convertToSwaggerDoctor(updatedDoctor), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<com.manulife.ap.rsf_connect_mongodb.swagger.model.Doctor> createDoctor(com.manulife.ap.rsf_connect_mongodb.swagger.model.Doctor swaggerDoctor) {
        Doctor mongoDoctor = new Doctor();
        mongoDoctor.setName(swaggerDoctor.getName());
        mongoDoctor.setGender(swaggerDoctor.getGender());
        
        Doctor savedDoctor = doctorRepository.save(mongoDoctor).block();
        return new ResponseEntity<>(convertToSwaggerDoctor(savedDoctor), HttpStatus.CREATED);
    }

    private com.manulife.ap.rsf_connect_mongodb.swagger.model.Doctor convertToSwaggerDoctor(Doctor mongoDoctor) {
        com.manulife.ap.rsf_connect_mongodb.swagger.model.Doctor swaggerDoctor = new com.manulife.ap.rsf_connect_mongodb.swagger.model.Doctor();
        swaggerDoctor.setId(mongoDoctor.getId());
        swaggerDoctor.setName(mongoDoctor.getName());
        swaggerDoctor.setGender(mongoDoctor.getGender());
        return swaggerDoctor;
    }
}
