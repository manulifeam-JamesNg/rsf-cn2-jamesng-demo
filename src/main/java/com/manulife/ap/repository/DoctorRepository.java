package com.manulife.ap.repository;

import com.manulife.ap.model.Doctor;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface DoctorRepository extends ReactiveMongoRepository<Doctor, String> {
    
    @Query("{'name': { $regex: ?0 } }")
    Flux<Doctor> findDoctorByRegExName(String name);
}
