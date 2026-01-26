package com.manulife.ap.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/beat")
@Api(tags = "Utils", description = "Heart Beat Controller")
public class HeartBeatController {

    @GetMapping
    @ApiOperation(value = "beat", notes = "Health check endpoint")
    public String beat() {
        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            return hostAddress + " is alive";
        } catch (UnknownHostException e) {
            return "Server is alive";
        }
    }
}
