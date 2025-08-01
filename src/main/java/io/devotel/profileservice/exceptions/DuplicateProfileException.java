package io.devotel.profileservice.exceptions;

public class DuplicateProfileException extends RuntimeException {
    public DuplicateProfileException(Long userId) {
        super("Profile already exists for userId: " + userId);
    }
}