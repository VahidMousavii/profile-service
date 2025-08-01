package io.devotel.profileservice.profile.service;

import io.devotel.profileservice.exceptions.DuplicateProfileException;
import io.devotel.profileservice.exceptions.ProfileNotFoundException;
import io.devotel.profileservice.exceptions.UserNotFoundException;
import io.devotel.profileservice.mapper.ProfileMapper;
import io.devotel.profileservice.profile.dto.AddProfileDTO;
import io.devotel.profileservice.profile.dto.ProfileResponseDTO;
import io.devotel.profileservice.profile.entity.ProfileEntity;
import io.devotel.profileservice.profile.repository.ProfileRepository;
import io.devotel.profileservice.soap.client.UserSoapClient;
import io.devotel.profileservice.soap.wsdl.GetUserByIdResponse;
import io.devotel.profileservice.soap.wsdl.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserSoapClient userSoapClient;
    private final ProfileMapper profileMapper;

    @Override
    public ProfileResponseDTO addProfile(AddProfileDTO addProfileDTO) {
        log.info("Adding new profile for userId={}", addProfileDTO.getUserId());
        if (doesUserExist(addProfileDTO)) {
            log.warn("Duplicate profile detected for userId={}", addProfileDTO.getUserId());
            throw new DuplicateProfileException(addProfileDTO.getUserId());
        }
        GetUserByIdResponse userResponse = userSoapClient.getUserById(addProfileDTO.getUserId());
        User user = userResponse.getUser();
        if (isUserNull(user)) {
            log.warn("User not found with id={}", addProfileDTO.getUserId());
            throw new UserNotFoundException(addProfileDTO.getUserId());
        }
        ProfileEntity entity = profileMapper.toEntity(addProfileDTO);
        ProfileEntity saved = profileRepository.save(entity);
        ProfileResponseDTO response = profileMapper.toDto(entity);
        response.setUser(user);
        log.info("Profile saved with id={}", saved.getId());
        return response;
    }

    private static boolean isUserNull(User user) {
        return user == null;
    }

    private boolean doesUserExist(AddProfileDTO addProfileDTO) {
        return profileRepository.existsByUserId(addProfileDTO.getUserId());
    }

    @Override
    public ProfileResponseDTO getProfileById(Long id) {
        log.info("Fetching profile for id={}", id);

        ProfileEntity entity = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException(id));

        GetUserByIdResponse userResponse = userSoapClient.getUserById(entity.getUserId());
        User user = userResponse.getUser();

        ProfileResponseDTO response = profileMapper.toDto(entity);
        response.setUser(user);

        return response;
    }
}
