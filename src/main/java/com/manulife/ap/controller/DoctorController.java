package com.manulife.ap.controller;

import com.manulife.ap.model.Doctor;
import com.manulife.ap.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Mono<Doctor> getDoctorById(@PathVariable String id) {
        return doctorRepository.findById(id);
    }
    
    @GetMapping("/search/{name}")
    public Flux<Doctor> getDoctorByName(@PathVariable String name) {
        return doctorRepository.findDoctorByRegExName(name);
    }
    
    @PostMapping
    public Mono<Doctor> createDoctor(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }
    
    @PutMapping("/{id}")
    public Mono<Doctor> updateDoctor(@PathVariable String id, @RequestBody Doctor doctor) {
        return doctorRepository.findById(id)
                .flatMap(existingDoctor -> {
                    existingDoctor.setName(doctor.getName());
                    existingDoctor.setGender(doctor.getGender());
                    return doctorRepository.save(existingDoctor);
                });
    }
    
    @DeleteMapping("/{id}")
    public Mono<Void> deleteDoctor(@PathVariable String id) {
        return doctorRepository.deleteById(id);
    }
    
    @DeleteMapping
    public Mono<Void> deleteAllDoctors() {
        return doctorRepository.deleteAll();
    }
}
