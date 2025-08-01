package io.devotel.profileservice.mapper;

import io.devotel.profileservice.profile.dto.AddProfileDTO;
import io.devotel.profileservice.profile.dto.ProfileResponseDTO;
import io.devotel.profileservice.profile.entity.ProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(target = "id", ignore = true)
        // prevent accidental ID overwrite
    ProfileEntity toEntity(AddProfileDTO dto);

    @Mapping(target = "user", ignore = true)
    ProfileResponseDTO toDto(ProfileEntity entity); // user comes from SOAP CALL
}