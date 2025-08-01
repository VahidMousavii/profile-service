package io.devotel.profileservice.profile.repository;

import io.devotel.profileservice.profile.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    boolean existsByUserId(Long userId);
}
