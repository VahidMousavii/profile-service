package io.devotel.profileservice.exceptions;

import io.devotel.profileservice.common.StaticStrings;

public class ProfileNotFoundException extends RuntimeException {

    public ProfileNotFoundException(Long profileId) {
        super(StaticStrings.PROFILE_NOT_FOUND_WITH_ID + profileId);
    }

    public ProfileNotFoundException(String message) {
        super(message);
    }
}
