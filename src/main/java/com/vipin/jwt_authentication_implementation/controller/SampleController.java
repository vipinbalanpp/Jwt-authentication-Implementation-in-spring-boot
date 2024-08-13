package com.vipin.jwt_authentication_implementation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sample")
@RequiredArgsConstructor
public class SampleController {
    @GetMapping()
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello user from secured end point") ;
    }
}
