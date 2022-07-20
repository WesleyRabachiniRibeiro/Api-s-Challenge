package com.savelife.user.repositories;

import com.savelife.user.entities.Phone;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
    Page<Phone> findAll(Pageable pageable);
}
