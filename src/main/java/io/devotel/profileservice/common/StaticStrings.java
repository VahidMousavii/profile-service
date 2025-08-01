package io.devotel.profileservice.common;

/**
 * Central place for all static string constants used in responses, logs, etc.
 */
public final class StaticStrings {

    private StaticStrings() {
    }

    public static final String USER_NOT_FOUND_WITH_ID = "User not found";
    public static final String ERROR_IN_CALLING_USER_SERVICE = "Error in calling user service";
    public static final String PROFILE_CREATED_SUCCESSFULLY = "Profile created successfully";
    public static final String PROFILE_NOT_FOUND_WITH_ID = "Profile not found with id: ";
    public static final String FAILED_TO_FETCH_PROFILE = "Failed to fetch profile";
    public static final String PROFILE_FETCHED_SUCCESSFULLY = "Profile fetched successfully";
}