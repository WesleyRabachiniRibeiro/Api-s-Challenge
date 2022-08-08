package com.savelife.hospital.services;

import com.savelife.hospital.entities.Hospital;
import com.savelife.hospital.repositories.HospitalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class HospitalService {

    private final HospitalRepository repository;

    private  HospitalService(HospitalRepository repository){
        this.repository = repository;
    }

    public Hospital saveHospital(Hospital user){
        return repository.save(user);
    }

    public Hospital findHospital(Long id){
        Optional<Hospital> user = repository.findById(id);
        return user.orElseThrow(() -> new EntityNotFoundException("Hospital Not Found!"));
    }

    public Page<Hospital> listAllHospitals(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Hospital updateHospital(Hospital updatedHospital, Long id){
        Hospital hospital = this.findHospital(id);
        updatedHospital.setId(hospital.getId());
        return repository.save(updatedHospital);
    }

    public void deleteHospital(Long id){
        Hospital hospital = this.findHospital(id);
        repository.delete(hospital);
    }
}
