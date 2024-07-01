package com.stoyanov.gateway.application.service;

import com.stoyanov.gateway.application.dto.ProfileDTO;
import com.stoyanov.gateway.application.dto.ProfileWithPasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Service
public class ProfileService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PROFILE_SERVICE_BASE_URL = "http://localhost:8088/v1/profile";

    public ProfileDTO create(ProfileWithPasswordDTO profileWithPasswordDTO) {
        HttpEntity<ProfileWithPasswordDTO> request = new HttpEntity<>(profileWithPasswordDTO);
        ResponseEntity<ProfileDTO> response = restTemplate.exchange(PROFILE_SERVICE_BASE_URL, HttpMethod.POST, request, ProfileDTO.class);
        return response.getBody();
    }

    public ProfileDTO getById(UUID id) {
        String url = PROFILE_SERVICE_BASE_URL + "/" + id;
        ResponseEntity<ProfileDTO> response = restTemplate.exchange(url, HttpMethod.GET, null, ProfileDTO.class);
        return response.getBody();
    }

    public ProfileDTO update(UUID id, ProfileDTO profileDTO) {
        String url = PROFILE_SERVICE_BASE_URL + "/" + id;
        HttpEntity<ProfileDTO> request = new HttpEntity<>(profileDTO);
        ResponseEntity<ProfileDTO> response = restTemplate.exchange(url, HttpMethod.PUT, request, ProfileDTO.class);
        return response.getBody();
    }

    public void delete(UUID id) {
        String url = PROFILE_SERVICE_BASE_URL + "/" + id;
        restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
    }
}
