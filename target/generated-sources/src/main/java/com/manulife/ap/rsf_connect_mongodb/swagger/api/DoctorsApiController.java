package com.manulife.ap.rsf_connect_mongodb.swagger.api;

import com.manulife.ap.rsf_connect_mongodb.swagger.model.Doctor;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import java.util.List;

@io.swagger.annotations.Api(tags = "doctors")
@org.springframework.web.bind.annotation.RestController
@org.springframework.web.bind.annotation.RequestMapping("/james")
public class DoctorsApiController implements DoctorsApi {

    private final DoctorsApiDelegate delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public DoctorsApiController(DoctorsApiDelegate delegate) {
        this.delegate = delegate;
    }
    @org.springframework.web.bind.annotation.RequestMapping(value = "/doctors", 
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = org.springframework.web.bind.annotation.RequestMethod.POST)
    public ResponseEntity<Doctor> createDoctor(@ApiParam(value = "Doctor object to be created" ,required=true )  @Valid @RequestBody Doctor doctor) {
        return delegate.createDoctor(doctor);
    }

    @org.springframework.web.bind.annotation.RequestMapping(value = "/doctors/{id}", 
        produces = { "application/json" }, 
        method = org.springframework.web.bind.annotation.RequestMethod.DELETE)
    public ResponseEntity<Void> deleteDoctor(@ApiParam(value = "Doctor ID",required=true) @PathVariable("id") String id) {
        return delegate.deleteDoctor(id);
    }

    @org.springframework.web.bind.annotation.RequestMapping(value = "/doctors", 
        produces = { "application/json" }, 
        method = org.springframework.web.bind.annotation.RequestMethod.GET)
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return delegate.getAllDoctors();
    }

    @org.springframework.web.bind.annotation.RequestMapping(value = "/doctors/{id}", 
        produces = { "application/json" }, 
        method = org.springframework.web.bind.annotation.RequestMethod.GET)
    public ResponseEntity<Doctor> getDoctorById(@ApiParam(value = "Doctor ID",required=true) @PathVariable("id") String id) {
        return delegate.getDoctorById(id);
    }

    @org.springframework.web.bind.annotation.RequestMapping(value = "/doctors/{id}", 
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = org.springframework.web.bind.annotation.RequestMethod.PUT)
    public ResponseEntity<Doctor> updateDoctor(@ApiParam(value = "Doctor ID",required=true) @PathVariable("id") String id,@ApiParam(value = "Updated doctor object" ,required=true )  @Valid @RequestBody Doctor doctor) {
        return delegate.updateDoctor(id, doctor);
    }

}
