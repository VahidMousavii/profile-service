package io.devotel.profileservice.profile.service;

import io.devotel.profileservice.common.GeneralResponseDto;
import io.devotel.profileservice.profile.dto.AddProfileDTO;
import io.devotel.profileservice.profile.dto.ProfileResponseDTO;

public interface ProfileService {

    /**
     * Stores a new profile in the database and enriches it with user data via SOAP.
     * 
     * @param addProfileDTO the profile data to be added
     * @return the full profile data including user info
     */
    GeneralResponseDto<ProfileResponseDTO> addProfile(AddProfileDTO addProfileDTO);

    /**
     * Retrieves a profile by its ID and attaches corresponding user data via SOAP.
     * 
     * @param id the profile ID
     * @return the full profile data including user info
     */
    GeneralResponseDto<ProfileResponseDTO> getProfileById(Long id);
}
