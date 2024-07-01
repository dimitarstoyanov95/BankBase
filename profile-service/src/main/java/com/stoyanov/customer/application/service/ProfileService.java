package com.stoyanov.customer.application.service;

import com.stoyanov.customer.application.dto.ProfileDTO;
import com.stoyanov.customer.application.dto.ProfilePasswordDTO;
import com.stoyanov.customer.application.mapper.ProfileMapper;
import com.stoyanov.customer.domain.model.Profile;
import com.stoyanov.customer.domain.repository.ProfileRepository;
import com.stoyanov.customer.domain.exception.ProfileDuplicationException;
import com.stoyanov.customer.domain.exception.ProfileNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileMapper profileMapper;


    public ProfileDTO get(UUID id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException(id));
        return profileMapper.toDto(profile);
    }

    public List<ProfileDTO> getAll() {
        List<Profile> profiles = profileRepository.findAll();
        return profileMapper.toDtoList(profiles);
    }

    public ProfileDTO create(ProfilePasswordDTO profilePasswordDTO) {
        if (profileRepository.findByEmail(profilePasswordDTO.getEmail()) == null) {
            Profile profile = profileMapper.toPasswordEntity(profilePasswordDTO);
            profileRepository.save(profile);
            return profileMapper.toDto(profile);
        }
        throw new ProfileDuplicationException(profilePasswordDTO.getEmail());
    }

    public ProfileDTO update(UUID id, ProfileDTO profileDTO) {
        Profile existingProfile = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException(id));

        Profile updatedProfile = profileMapper.toEntity(profileDTO);
        updatedProfile.setId(existingProfile.getId());
        profileRepository.save(updatedProfile);

        return profileMapper.toDto(updatedProfile);
    }

    public void delete(UUID id) {
        Profile existingProfile = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException(id));
        profileRepository.delete(existingProfile);
    }
}
