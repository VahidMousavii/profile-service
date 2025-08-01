package io.devotel.profileservice.profile.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class AddProfileDTO {

    @NotNull
    private Long userId;

    @NotBlank
    private String bio;

    @NotBlank
    private String location;

    @Min(0)
    private Integer age;
}
