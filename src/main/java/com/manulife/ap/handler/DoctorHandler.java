package com.manulife.ap.handler;

import com.manulife.ap.model.Doctor;
import com.manulife.ap.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class DoctorHandler {
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(doctorRepository.findAll(), Doctor.class);
    }
    
    public Mono<ServerResponse> getById(ServerRequest request) {
        String id = request.pathVariable("id");
        return doctorRepository.findById(id)
                .flatMap(doctor -> ServerResponse.ok().bodyValue(doctor))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
    
    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<Doctor> doctorMono = request.bodyToMono(Doctor.class);
        return doctorMono
                .flatMap(doctorRepository::save)
                .flatMap(doctor -> ServerResponse.ok().bodyValue(doctor));
    }
    
    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Doctor> doctorMono = request.bodyToMono(Doctor.class);
        
        return doctorMono
                .flatMap(doctor -> doctorRepository.findById(id)
                        .flatMap(existingDoctor -> {
                            existingDoctor.setName(doctor.getName());
                            existingDoctor.setGender(doctor.getGender());
                            return doctorRepository.save(existingDoctor);
                        }))
                .flatMap(updatedDoctor -> ServerResponse.ok().bodyValue(updatedDoctor))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
    
    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return doctorRepository.deleteById(id)
                .then(ServerResponse.ok().build());
    }
}
