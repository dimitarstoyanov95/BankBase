package com.stoyanov.gateway.application.controller;


import com.stoyanov.gateway.application.dto.ProfileDTO;
import com.stoyanov.gateway.application.dto.ProfileWithPasswordDTO;
import com.stoyanov.gateway.application.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getCustomerById(@PathVariable UUID id) {
        ProfileDTO customer = profileService.getById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileWithPasswordDTO profileWithPasswordDTO) {
        ProfileDTO profileDTO = profileService.create(profileWithPasswordDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(profileDTO);
    }
}