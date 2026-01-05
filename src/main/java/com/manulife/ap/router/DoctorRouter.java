package com.manulife.ap.router;

import com.manulife.ap.handler.DoctorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class DoctorRouter {
    
    @Bean
    public RouterFunction<ServerResponse> route(DoctorHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/func/doctors")
                        .and(RequestPredicates.accept(MediaType.TEXT_EVENT_STREAM)), 
                        handler::getAll)
                .andRoute(RequestPredicates.GET("/func/doctors/{id}"), 
                        handler::getById)
                .andRoute(RequestPredicates.POST("/func/doctors"), 
                        handler::create)
                .andRoute(RequestPredicates.PUT("/func/doctors/{id}"), 
                        handler::update)
                .andRoute(RequestPredicates.DELETE("/func/doctors/{id}"), 
                        handler::delete);
    }
}
