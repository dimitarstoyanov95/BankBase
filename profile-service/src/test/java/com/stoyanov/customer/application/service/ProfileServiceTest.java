package com.stoyanov.customer.application.service;

import com.stoyanov.customer.application.dto.ProfileDTO;
import com.stoyanov.customer.application.mapper.ProfileMapper;
import com.stoyanov.customer.domain.model.Profile;
import com.stoyanov.customer.domain.repository.ProfileRepository;
import com.stoyanov.customer.domain.exception.ProfileDuplicationException;
import com.stoyanov.customer.domain.exception.ProfileNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProfileServiceTest {

    private ProfileRepository profileRepository;
    private ProfileMapper profileMapper;
    private ProfileService profileService;

    @BeforeEach
    void setUp() {
        profileRepository = mock(ProfileRepository.class);
        profileMapper = mock(ProfileMapper.class);
        profileService = new ProfileService(profileRepository, profileMapper);
    }

    @Test
    void testGet_whenCustomerExists_shouldReturnCustomerDTO() {
        // Given
        UUID id = UUID.randomUUID();
        Profile profile = createCustomer("Uncle", "Bob", "unclebob@example.com");
        when(profileRepository.findById(id)).thenReturn(Optional.of(profile));
        when(profileMapper.toDto(profile)).thenReturn(createCustomerDTO("Uncle", "Bob", "unclebob@example.com"));

        // When
        ProfileDTO result = profileService.get(id);

        // Then
        assertThat(result.getFirstName()).isEqualTo(profile.getFirstName());
        verify(profileRepository).findById(id);
        verify(profileMapper).toDto(profile);
    }

    @Test
    void testGet_whenCustomerDoesNotExist_shouldThrowException() {
        // Given
        UUID id = UUID.randomUUID();
        when(profileRepository.findById(id)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ProfileNotFoundException.class, () -> profileService.get(id));
        verify(profileRepository).findById(id);
    }

    @Test
    void testGetAll_shouldReturnListOfCustomerDTOs() {
        // Given
        Profile profile = createCustomer("Mark", "Richards", "markrichards@example.com");
        List<Profile> profiles = List.of(profile);
        when(profileRepository.findAll()).thenReturn(profiles);
        when(profileMapper.toDtoList(profiles)).thenReturn(List.of(createCustomerDTO("Mark", "Richards", "markrichards@example.com")));

        // When
        List<ProfileDTO> result = profileService.getAll();

        // Then
        assertThat(result).hasSize(1);
        verify(profileRepository).findAll();
        verify(profileMapper).toDtoList(profiles);
    }

    @Test
    void testCreate_whenEmailDoesNotExist_shouldCreateCustomer() {
        // Given
        ProfileDTO profileDTO = createCustomerDTO("Viktor", "Rentea", "viktorentea@example.com");
        Profile profile = createCustomer("Viktor", "Rentea", "viktorentea@example.com");
        when(profileRepository.findByEmail(profileDTO.getEmail())).thenReturn(null);
        when(profileMapper.toEntity(profileDTO)).thenReturn(profile);
        when(profileMapper.toDto(profile)).thenReturn(profileDTO);
        when(profileRepository.save(profile)).thenReturn(profile);

        // When
        ProfileDTO result = profileService.create(profileDTO);

        // Then
        assertThat(result.getEmail()).isEqualTo(profileDTO.getEmail());
        verify(profileRepository).findByEmail(profileDTO.getEmail());
        verify(profileMapper).toEntity(profileDTO);
        verify(profileRepository).save(profile);
        verify(profileMapper).toDto(profile);
    }

    @Test
    void testCreate_whenEmailExists_shouldThrowException() {
        // Given
        ProfileDTO profileDTO = createCustomerDTO("Uncle", "Bob", "unclebob@example.com");
        Profile existingProfile = createCustomer("Uncle", "Bob", "unclebob@example.com");
        when(profileRepository.findByEmail(profileDTO.getEmail())).thenReturn(existingProfile);

        // When / Then
        assertThrows(ProfileDuplicationException.class, () -> profileService.create(profileDTO));
        verify(profileRepository).findByEmail(profileDTO.getEmail());
    }

    @Test
    void testUpdate_whenCustomerExists_shouldUpdateCustomer() {
        // Given
        UUID id = UUID.randomUUID();
        ProfileDTO profileDTO = createCustomerDTO("Mark", "Richards", "markrichards@example.com");
        Profile existingProfile = createCustomer("Viktor", "Rentea", "viktorentea@example.com");
        when(profileRepository.findById(id)).thenReturn(Optional.of(existingProfile));
        Profile updatedProfile = createCustomer("Mark", "Richards", "markrichards@example.com");
        updatedProfile.setId(id);
        when(profileMapper.toEntity(profileDTO)).thenReturn(updatedProfile);
        when(profileMapper.toDto(updatedProfile)).thenReturn(profileDTO);
        when(profileRepository.save(updatedProfile)).thenReturn(updatedProfile);

        // When
        ProfileDTO result = profileService.update(id, profileDTO);

        // Then
        assertThat(result.getEmail()).isEqualTo(profileDTO.getEmail());
        verify(profileRepository).findById(id);
        verify(profileMapper).toEntity(profileDTO);
        verify(profileRepository).save(updatedProfile);
        verify(profileMapper).toDto(updatedProfile);
    }

    @Test
    void testUpdate_whenCustomerDoesNotExist_shouldThrowException() {
        // Given
        UUID id = UUID.randomUUID();
        ProfileDTO profileDTO = createCustomerDTO("Viktor", "Rentea", "viktorentea@example.com");
        when(profileRepository.findById(id)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ProfileNotFoundException.class, () -> profileService.update(id, profileDTO));
        verify(profileRepository).findById(id);
    }

    @Test
    void testDelete_whenCustomerExists_shouldDeleteCustomer() {
        // Given
        UUID id = UUID.randomUUID();
        Profile existingProfile = createCustomer("Uncle", "Bob", "unclebob@example.com");
        when(profileRepository.findById(id)).thenReturn(Optional.of(existingProfile));

        // When
        profileService.delete(id);

        // Then
        verify(profileRepository).findById(id);
        verify(profileRepository).delete(existingProfile);
    }

    @Test
    void testDelete_whenCustomerDoesNotExist_shouldThrowException() {
        // Given
        UUID id = UUID.randomUUID();
        when(profileRepository.findById(id)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ProfileNotFoundException.class, () -> profileService.delete(id));
        verify(profileRepository).findById(id);
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
