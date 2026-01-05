package com.manulife.ap.delegateimpl;

import com.manulife.ap.repository.DoctorRepository;
import com.manulife.ap.rsf_connect_mongodb.swagger.api.HomeApiDelegate;
import com.manulife.ap.rsf_connect_mongodb.swagger.model.Doctor;
import com.manulife.ap.rsf_connect_mongodb.swagger.model.HomeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * HomeApiDelegateImpl - Implements the Swagger-generated HomeApiDelegate interface
 * This connects the Swagger API definition with the MongoDB repository
 */
@Component
public class HomeApiDelegateImpl implements HomeApiDelegate {
    
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public ResponseEntity<HomeResponse> homeGet() {
        List<Doctor> doctors = doctorRepository.findAll()
                .map(doctor -> {
                    Doctor swaggerDoctor = new Doctor();
                    swaggerDoctor.setId(doctor.getId());
                    swaggerDoctor.setName(doctor.getName());
                    swaggerDoctor.setGender(doctor.getGender());
                    return swaggerDoctor;
                })
                .collectList()
                .block();
        
        HomeResponse homeResponse = new HomeResponse();
        homeResponse.setAnswerEntity(doctors);
        return new ResponseEntity<>(homeResponse, HttpStatus.OK);
    }
}
