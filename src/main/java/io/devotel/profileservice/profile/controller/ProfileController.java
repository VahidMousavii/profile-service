package io.devotel.profileservice.profile.controller;

import io.devotel.profileservice.common.GeneralResponseDto;
import io.devotel.profileservice.profile.dto.AddProfileDTO;
import io.devotel.profileservice.profile.dto.ProfileResponseDTO;
import io.devotel.profileservice.profile.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<GeneralResponseDto<ProfileResponseDTO>> addProfile(@Valid @RequestBody AddProfileDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(profileService.addProfile(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponseDto<ProfileResponseDTO>> getProfileById(@PathVariable Long id) {
        return ResponseEntity
                .ok(profileService.getProfileById(id));
    }
}
