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
 * Annotation Way (Controller approach)
 * REST API for /doctors endpoints
 */
@RestController
@RequestMapping("/doctors")
public class DoctorController {
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    /**
     * GET /doctors - Retrieve all Doctor records
     */
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    
    /**
     * GET /doctors/{doctorId} - Retrieve a single doctor record
     */
    @GetMapping("/{doctorId}")
    public Mono<Doctor> getDoctorById(@PathVariable("doctorId") String id) {
        return doctorRepository.findById(id);
    }
    
    /**
     * GET /doctors/search/{name} - Search doctors by name using regex
     */
    @GetMapping("/search/{name}")
    public Flux<Doctor> getDoctorByName(@PathVariable String name) {
        return doctorRepository.findDoctorByRegExName(name);
    }
    
    /**
     * POST /doctors - Create a doctor record
     */
    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Flux<Doctor> doctorFlux = Flux.just(doctor);
        return doctorRepository.insert(doctorFlux);
    }
    
    /**
     * PUT /doctors/{doctorId} - Update a doctor by ID
     */
    @PutMapping("/{doctorId}")
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
     * DELETE /doctors - Delete all doctor records
     */
    @DeleteMapping
    public Mono<Void> deleteAllDoctors() {
        return doctorRepository.deleteAll();
    }
    
    /**
     * DELETE /doctors/{doctorId} - Delete a single doctor record
     */
    @DeleteMapping("/{doctorId}")
    public Mono<Void> deleteDoctorById(@PathVariable("doctorId") String id) {
        return doctorRepository.deleteById(id);
    }
}
