package com.manulife.ap.rsf_connect_mongodb.swagger.api;

import com.manulife.ap.rsf_connect_mongodb.swagger.model.Doctor;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * A delegate to be called by the {@link DoctorsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */

public interface DoctorsApiDelegate {

    /**
     * @see DoctorsApi#createDoctor
     */
    ResponseEntity<Doctor> createDoctor(Doctor doctor);

    /**
     * @see DoctorsApi#deleteDoctor
     */
    ResponseEntity<Void> deleteDoctor(String id);

    /**
     * @see DoctorsApi#getAllDoctors
     */
    ResponseEntity<List<Doctor>> getAllDoctors();

    /**
     * @see DoctorsApi#getDoctorById
     */
    ResponseEntity<Doctor> getDoctorById(String id);

    /**
     * @see DoctorsApi#updateDoctor
     */
    ResponseEntity<Doctor> updateDoctor(String id,
        Doctor doctor);

}
