package com.savelife.project.repositories;

import com.savelife.project.entities.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    Page<Hospital> findAll(Pageable pageable);

    @Query("select u from Hospital u where u.address.street = :street")
    Hospital findByAddressStreet(@Param("street")String street);
}
