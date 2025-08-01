package io.devotel.profileservice.exceptions;

import io.devotel.profileservice.common.StaticStrings;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long userId) {
        super(StaticStrings.USER_NOT_FOUND_WITH_ID + userId);
    }
}