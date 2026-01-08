package com.manulife.ap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * Cross-domain Configuration
 */
@Configuration
public class CorsConfig {
    
    @Bean
    public CorsWebFilter corsWebFilter() {
        // Select under reactive package based on url cross-domain
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        // Cross-domain configuration information
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // Allow cross-domain headers
        corsConfiguration.addAllowedHeader("*");
        // Allow cross-domain request models
        corsConfiguration.addAllowedMethod("*");
        // Allow cross-domain request sources
        corsConfiguration.addAllowedOrigin("*");
        // Whether to allow cookies across domains
        //corsConfiguration.setAllowCredentials(true);
        // Cross-domain configuration is required for any url
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(source);
    }
}
