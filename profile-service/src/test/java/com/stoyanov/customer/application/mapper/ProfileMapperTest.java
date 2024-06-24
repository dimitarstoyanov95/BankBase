package com.stoyanov.customer.application.mapper;

import com.stoyanov.customer.application.dto.ProfileDTO;
import com.stoyanov.customer.domain.model.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ProfileMapperTest {

    private ProfileMapper profileMapper;

    @BeforeEach
    void setUp() {
        profileMapper = new ProfileMapper();
    }

    @Test
    void testToDto_whenGivenCustomer_shouldReturnCustomerDTO() {
        // Given
        Profile profile = createCustomer("Viktor", "Rentea", "viktorentea@gmail.com");

        // When
        ProfileDTO dto = profileMapper.toDto(profile);

        // Then
        assertThat(dto.getFirstName()).isEqualTo(profile.getFirstName());
        assertThat(dto.getLastName()).isEqualTo(profile.getLastName());
        assertThat(dto.getEmail()).isEqualTo(profile.getEmail());
    }

    @Test
    void testToEntity_whenGivenCustomerDTO_shouldReturnCustomer() {
        // Given
        ProfileDTO profileDTO = createCustomerDTO("Viktor", "Rentea", "viktorentea@gmail.com");

        // When
        Profile profile = profileMapper.toEntity(profileDTO);

        // Then
        assertThat(profile.getFirstName()).isEqualTo(profileDTO.getFirstName());
        assertThat(profile.getLastName()).isEqualTo(profileDTO.getLastName());
        assertThat(profile.getEmail()).isEqualTo(profileDTO.getEmail());
    }

    @Test
    void testToDtoList_whenGivenListOfCustomers_shouldReturnListOfCustomerDTOs() {
        // Given
        Profile firstProfile = createCustomer("Venkat", "Subramaniam", "subramaniam@gmail.com");
        Profile secondProfile = createCustomer("Viktor", "Rentea", "viktorentea@gmail.com");
        List<Profile> profiles = List.of(firstProfile, secondProfile);

        // When
        List<ProfileDTO> profileDTOS = profileMapper.toDtoList(profiles);

        // Then
        assertThat(profileDTOS).hasSize(2);
        assertThat(profileDTOS.get(0).getFirstName()).isEqualTo(firstProfile.getFirstName());
        assertThat(profileDTOS.get(1).getFirstName()).isEqualTo(secondProfile.getFirstName());
    }

    @Test
    void testToEntityList_whenGivenListOfCustomerDTOs_shouldReturnListOfCustomers() {
        // Given
        ProfileDTO firstProfileDto = createCustomerDTO("Venkat", "Subramaniam", "subramaniam@gmail.com");
        ProfileDTO secondProfileDto = createCustomerDTO("Viktor", "Rentea", "viktorentea@gmail.com");
        List<ProfileDTO> dtos = List.of(firstProfileDto, secondProfileDto);

        // When
        List<Profile> profiles = profileMapper.toEntityList(dtos);

        // Then
        assertThat(profiles).hasSize(2);
        assertThat(profiles.get(0).getFirstName()).isEqualTo(firstProfileDto.getFirstName());
        assertThat(profiles.get(1).getFirstName()).isEqualTo(secondProfileDto.getFirstName());
    }

    private Profile createCustomer(String firstName, String lastName, String email) {
        Profile profile = new Profile();
        profile.setId(UUID.randomUUID());
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setEmail(email);
        return profile;
    }

    private ProfileDTO createCustomerDTO(String firstName, String lastName, String email) {
        ProfileDTO dto = new ProfileDTO();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(email);
        return dto;
    }
}
