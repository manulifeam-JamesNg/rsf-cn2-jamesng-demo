package com.manulife.ap.handler;

import com.manulife.ap.model.Doctor;
import com.manulife.ap.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Stream Way (Functional Programming) Handler
 * Handles requests for /James/fun/doctors endpoints
 */
@Component
public class DoctorHandler {
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    /**
     * Test endpoint: GET /James/hello
     */
    public Mono<ServerResponse> test(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("Hello World");
    }
    
    /**
     * Get all doctors: GET /James/fun/doctors
     * Returns TEXT_EVENT_STREAM
     */
    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(doctorRepository.findAll(), Doctor.class);
    }
    
    /**
     * Get doctor by ID: GET /James/doctors/{doctorId}
     */
    public Mono<ServerResponse> getById(ServerRequest request) {
        String doctorId = request.pathVariable("doctorId");
        return doctorRepository.findById(doctorId)
                .flatMap(doctor -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(doctor))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
    
    /**
     * Insert doctor: POST /<yourname>/insert
     * Uses bodyToFlux and insert() as shown in requirements
     */
    public Mono<ServerResponse> insert(ServerRequest request) {
        Flux<Doctor> doctorFlux = request.bodyToFlux(Doctor.class);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(doctorRepository.insert(doctorFlux), Doctor.class);
    }
    
    /**
     * Update doctor: PUT /<yourname>/doctors/{doctorId}
     * Uses flatMap pattern as shown in requirements
     */
    public Mono<ServerResponse> updateDoctor(ServerRequest request) {
        String doctorId = request.pathVariable("doctorId");
        Mono<Doctor> doctorMono = request.bodyToMono(Doctor.class);
        
        return doctorMono
                .flatMap(doctor -> doctorRepository.findById(doctorId)
                        .flatMap(existingDoctor -> {
                            existingDoctor.setName(doctor.getName());
                            existingDoctor.setGender(doctor.getGender());
                            return doctorRepository.save(existingDoctor);
                        }))
                .flatMap(updatedDoctor -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(updatedDoctor))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
    
    /**
     * Delete doctor by ID: DELETE /<yourname>/deleteById/{id}
     * Returns TEXT_EVENT_STREAM as shown in requirements
     */
    public Mono<ServerResponse> deleteById(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(doctorRepository.findById(id)
                        .flatMap(doctor -> doctorRepository.delete(doctor)
                                .thenReturn(doctor)), 
                        Doctor.class);
    }
}
