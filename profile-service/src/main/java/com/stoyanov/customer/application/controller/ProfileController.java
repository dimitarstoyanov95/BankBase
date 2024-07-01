package com.stoyanov.customer.application.controller;

import com.stoyanov.customer.application.dto.ProfileDTO;
import com.stoyanov.customer.application.dto.ProfilePasswordDTO;
import com.stoyanov.customer.application.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getById(@PathVariable UUID id) {
        ProfileDTO customer = profileService.get(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> getAll() {
        List<ProfileDTO> customers = profileService.getAll();
        return ResponseEntity.ok(customers);
    }

    @PostMapping
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfilePasswordDTO profilePasswordDTO) {
        ProfileDTO createdCustomer = profileService.create(profilePasswordDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable UUID id, @RequestBody ProfileDTO profileDTO) {
        ProfileDTO updatedCustomer = profileService.update(id, profileDTO);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        profileService.delete(id);
    }
}
