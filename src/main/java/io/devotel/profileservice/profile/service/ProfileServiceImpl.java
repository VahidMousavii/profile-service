package io.devotel.profileservice.profile.service;

import io.devotel.profileservice.common.GeneralResponseDto;
import io.devotel.profileservice.common.StaticStrings;
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
import org.springframework.ws.soap.client.SoapFaultClientException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserSoapClient userSoapClient;
    private final ProfileMapper profileMapper;

    @Override
    public GeneralResponseDto<ProfileResponseDTO> addProfile(AddProfileDTO addProfileDTO) {
        try {
            log.info("Adding new profile for userId={}", addProfileDTO.getUserId());

            if (profileRepository.existsByUserId(addProfileDTO.getUserId())) {
                log.warn("Duplicate profile detected for userId={}", addProfileDTO.getUserId());
                throw new DuplicateProfileException(addProfileDTO.getUserId());
            }

            GetUserByIdResponse userResponse = userSoapClient.getUserById(addProfileDTO.getUserId());
            User user = userResponse.getUser();

            if (user == null) {
                log.warn("User not found with id={}", addProfileDTO.getUserId());
                throw new UserNotFoundException(addProfileDTO.getUserId());
            }

            // preventing userId from mapping to id by profileMapper(MapStruct)
            ProfileEntity entity = profileMapper.toEntity(addProfileDTO);
            ProfileEntity saved = profileRepository.save(entity);

            ProfileResponseDTO response = profileMapper.toDto(saved);
            response.setUser(user);

            log.info("Profile saved with id={}", saved.getId());
            return GeneralResponseDto.<ProfileResponseDTO>builder()
                    .code(200)
                    .message(StaticStrings.PROFILE_CREATED_SUCCESSFULLY)
                    .data(response)
                    .build();

        } catch (Exception e) {
            log.error("Failed to add profile: {}", e.getMessage(), e);
            // for showing appropriate message to client
            if (e instanceof SoapFaultClientException) {
                throw new RuntimeException(StaticStrings.ERROR_IN_CALLING_USER_SERVICE);
            }
            throw new RuntimeException(StaticStrings.FAILED_TO_FETCH_PROFILE, e);
        }
    }

    @Override
    public GeneralResponseDto<ProfileResponseDTO> getProfileById(Long id) {
        try {
            log.info("Fetching profile for id={}", id);

            ProfileEntity entity = profileRepository.findById(id)
                    .orElseThrow(() -> new ProfileNotFoundException(id));

            GetUserByIdResponse userResponse = userSoapClient.getUserById(entity.getUserId());
            User user = userResponse.getUser();

            ProfileResponseDTO response = profileMapper.toDto(entity);
            response.setUser(user);

            return GeneralResponseDto.<ProfileResponseDTO>builder()
                    .code(200)
                    .message(StaticStrings.PROFILE_FETCHED_SUCCESSFULLY)
                    .data(response)
                    .build();

        } catch (ProfileNotFoundException e) {
            log.warn("Profile not found: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Failed to fetch profile by id={}: {}", id, e.getMessage(), e);
            throw new RuntimeException(StaticStrings.FAILED_TO_FETCH_PROFILE, e);
        }
    }
}
