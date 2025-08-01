package io.devotel.profileservice.exceptions;

import io.devotel.profileservice.common.GeneralResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponseDto<String>> handleUnexpectedException(Exception ex) {
        //we can log here in Handler layer, addition to service layer
        log.error("Unhandled exception occurred", ex);

        GeneralResponseDto<String> response = GeneralResponseDto.<String>builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An unexpected internal error occurred.")
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<GeneralResponseDto<String>> handleProfileNotFoundException(ProfileNotFoundException ex) {
        //we can log here in Handler layer, addition to service layer
        log.warn("Profile not found", ex);
        GeneralResponseDto<String> response = GeneralResponseDto.<String>builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GeneralResponseDto<String>> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                GeneralResponseDto.<String>builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .data(null)
                        .build()
        );
    }

    @ExceptionHandler(DuplicateProfileException.class)
    public ResponseEntity<GeneralResponseDto<String>> handleDuplicateProfileException(DuplicateProfileException ex) {
        GeneralResponseDto<String> response = GeneralResponseDto.<String>builder()
                .code(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
