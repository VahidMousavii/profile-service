package io.devotel.profileservice.profile.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_PROFILE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "c_bio")
    private String bio;

    @Column(name = "c_location")
    private String location;

    @Column(name = "c_age")
    private Integer age;

    @Column(name = "c_user_id", nullable = false, unique = true)
    private Long userId;
}
