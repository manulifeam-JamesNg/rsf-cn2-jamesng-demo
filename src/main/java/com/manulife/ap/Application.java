/*************************************************************************
 * Manulife Financial Service CONFIDENTIAL
 * 
 *  All Rights Reserved.
 * 
 * NOTICE:  All information contained herein is, and remains
 * the property of Manulife Financial Services,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Manulife Financial Services
 * and its suppliers and may be covered by patents in process, 
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Manulife Financial Services.
 */
package com.manulife.ap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Defines the application, supports service registry, circuit breaker 
 * for Spring Boot application.
 * 
 * @EnableJpaRepositories - add this to enable 
 * 
 * @author himanshu sharma
 * @since June 2017
 */
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableCaching
@SpringBootApplication
@ImportResource("classpath:service-config.xml")
@ComponentScan(basePackages = "com.manulife.ap.*, io.swagger")
@EnableReactiveMongoRepositories(basePackageClasses = com.manulife.ap.repository.DoctorRepository.class)
public class Application{
	@Autowired
	DiscoveryClient discoveryClient;
	
	/**
	 * Application start point.
	 * @param args
	 */
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);		
	}
}
