package com.manulife.ap.controller;

import com.manulife.ap.model.Doctor;
import com.manulife.ap.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST Controller for /James endpoints (AKS deployment)
 */
@RestController
@RequestMapping("/James")
public class JamesDoctorController {
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    /**
     * GET /James/hello - Test endpoint
     */
    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Hello World from James endpoint!");
    }
    
    /**
     * GET /James/fun/doctors - Retrieve all Doctor records
     */
    @GetMapping(value = "/fun/doctors", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    
    /**
     * POST /James/fun/doctors - Create a doctor record
     */
    @PostMapping(value = "/fun/doctors", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Doctor> insertDoctor(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }
    
    /**
     * GET /James/fun/doctors/{doctorId} - Retrieve a single doctor record
     */
    @GetMapping("/fun/doctors/{doctorId}")
    public Mono<Doctor> getDoctorById(@PathVariable("doctorId") String id) {
        return doctorRepository.findById(id);
    }
    
    /**
     * PUT /James/fun/doctors/{doctorId} - Update a doctor by ID
     */
    @PutMapping(value = "/fun/doctors/{doctorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Doctor>> updateDoctor(@PathVariable("doctorId") String id, @RequestBody Doctor doctor) {
        return doctorRepository.findById(id)
                .flatMap(d -> {
                    d.setName(doctor.getName());
                    d.setGender(doctor.getGender());
                    return doctorRepository.save(d);
                })
                .map(d -> new ResponseEntity<>(d, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * DELETE /James/fun/doctors/{doctorId} - Delete a single doctor record
     */
    @DeleteMapping(value = "/fun/doctors/{doctorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> deleteDoctorById(@PathVariable("doctorId") String id) {
        return doctorRepository.deleteById(id);
    }
}
