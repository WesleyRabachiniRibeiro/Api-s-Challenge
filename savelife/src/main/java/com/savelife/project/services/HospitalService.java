package com.savelife.project.services;

import com.savelife.project.dto.hospital.RegistryHospital;
import com.savelife.project.entities.Ambulance;
import com.savelife.project.entities.Hospital;
import com.savelife.project.mappers.HospitalMapper;
import com.savelife.project.repositories.AmbulanceRepository;
import com.savelife.project.repositories.HospitalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {

    private final HospitalRepository repository;
    private final AmbulanceRepository ambulanceRepository;

    private  HospitalService(HospitalRepository repository, AmbulanceRepository ambulanceRepository){
        this.repository = repository;
        this.ambulanceRepository = ambulanceRepository;
    }

    public Hospital saveHospital(RegistryHospital dto){
        List<Ambulance> ambulances = new ArrayList<>();
        dto.getAmbulances().forEach( id ->
                ambulances.add(ambulanceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ambulance not Found!")))
        );
        Hospital hospital = HospitalMapper.fromDTO(dto);


        hospital.setAmbulances(ambulances);
        return repository.save(hospital);
    }

    public Hospital findHospital(Long id){
        Optional<Hospital> hospital = repository.findById(id);
        return hospital.orElseThrow(() -> new EntityNotFoundException("Hospital Not Found!"));
    }

    public Page<Hospital> listAllHospitals(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Hospital updateHospital(RegistryHospital dto, Long id){
        Hospital hospital = this.findHospital(id);
        Hospital updatedHospital = HospitalMapper.fromDTO(dto);
        List<Ambulance> ambulances = new ArrayList<>();
        dto.getAmbulances().forEach( ambulanceId ->
                ambulances.add(ambulanceRepository.findById(ambulanceId).orElseThrow(() -> new EntityNotFoundException("Ambulance not Found!")))
        );
        updatedHospital.setId(hospital.getId());
        updatedHospital.setAmbulances(ambulances);
        return repository.save(updatedHospital);
    }

    public void deleteHospital(Long id){
        Hospital hospital = this.findHospital(id);
        repository.delete(hospital);
    }
}
