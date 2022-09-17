package com.savelife.project.repositories;

import com.savelife.project.entities.Ambulance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmbulanceRepository extends JpaRepository<Ambulance, Long> {

    Page<Ambulance> findAll(Pageable pageable);

}
