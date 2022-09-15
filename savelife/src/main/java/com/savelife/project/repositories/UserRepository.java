package com.savelife.project.repositories;

import com.savelife.project.entities.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    Page<UserModel> findAll(Pageable pageable);

    Optional<UserModel> findByPhone(String number);

    Optional<UserModel> findByEmail(String email);
}
