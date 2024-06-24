package com.stoyanov.customer.application.mapper;

import com.stoyanov.customer.application.dto.ProfileDTO;
import com.stoyanov.customer.domain.model.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfileMapper {

    public ProfileDTO toDto(Profile profile) {
        ProfileDTO dto = new ProfileDTO();
        dto.setFirstName(profile.getFirstName());
        dto.setLastName(profile.getLastName());
        dto.setEmail(profile.getEmail());
        return dto;
    }

    public Profile toEntity(ProfileDTO dto) {
        Profile entity = new Profile();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        return entity;
    }

    public List<ProfileDTO> toDtoList(List<Profile> profiles) {
        return profiles.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Profile> toEntityList(List<ProfileDTO> customers) {
        return customers.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}

