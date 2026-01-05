package com.manulife.ap.router;

import com.manulife.ap.handler.DoctorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Stream Way (Functional Programming approach)
 * Routes for /<yourname>/fun/doctors
 */
@Configuration
public class DoctorRouter {
    
    @Bean
    public RouterFunction<ServerResponse> route(DoctorHandler handler) {
        return RouterFunctions
                // Test endpoint as shown in sample
                .route(RequestPredicates.GET("/ngtszch/hello")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), 
                        handler::test)
                // GET /fun/doctors - Get all doctors with TEXT_EVENT_STREAM
                .andRoute(RequestPredicates.GET("/ngtszch/fun/doctors")
                        .and(RequestPredicates.accept(MediaType.TEXT_EVENT_STREAM)), 
                        handler::getAll)
                // POST /fun/doctors - Insert a doctor
                .andRoute(RequestPredicates.POST("/ngtszch/insert")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), 
                        handler::insert)
                // GET /doctors/{doctorId} - Get doctor by ID
                .andRoute(RequestPredicates.GET("/ngtszch/doctors/{doctorId}"), 
                        handler::getById)
                // PUT /doctors/{doctorId} - Update doctor
                .andRoute(RequestPredicates.PUT("/ngtszch/doctors/{doctorId}")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), 
                        handler::updateDoctor)
                // DELETE /deleteById/{id} - Delete doctor by ID
                .andRoute(RequestPredicates.DELETE("/ngtszch/deleteById/{id}")
                        .and(RequestPredicates.accept(MediaType.TEXT_EVENT_STREAM)), 
                        handler::deleteById);
    }
}
