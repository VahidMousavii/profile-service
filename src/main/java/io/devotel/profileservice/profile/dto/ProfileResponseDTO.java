package io.devotel.profileservice.profile.dto;

import io.devotel.profileservice.soap.wsdl.User;
import io.devotel.profileservice.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponseDTO {

    private Long id;

    private String bio;

    private String location;

    private Integer age;

    // response from user-service
    private User user;
}
