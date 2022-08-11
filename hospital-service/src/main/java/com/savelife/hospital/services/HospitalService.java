package com.savelife.hospital.services;

import com.savelife.hospital.entities.Hospital;
import com.savelife.hospital.entities.Phone;
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

    public Hospital saveHospital(Hospital hospital){
        hospital.getPhone().forEach(phone -> {
                phone.setHospital(hospital);
        });
        return repository.save(hospital);
    }

    public Hospital findHospital(Long id){
        Optional<Hospital> hospital = repository.findById(id);
        return hospital.orElseThrow(() -> new EntityNotFoundException("Hospital Not Found!"));
    }

    public Page<Hospital> listAllHospitals(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Hospital updateHospital(Hospital updatedHospital, Long id){
        Hospital hospital = this.findHospital(id);
        updatedHospital.setId(hospital.getId());
        for(int i = 0; i < updatedHospital.getPhone().size(); i++){
            updatedHospital.getPhone().get(i).setHospital(updatedHospital);
            updatedHospital.getPhone().get(i).setId(hospital.getPhone().get(i).getId());
        }
        updatedHospital.getAddress().setId(hospital.getAddress().getId());
        return repository.save(updatedHospital);
    }

    public void deleteHospital(Long id){
        Hospital hospital = this.findHospital(id);
        repository.delete(hospital);
    }
}
