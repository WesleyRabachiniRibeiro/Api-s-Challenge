package com.savelife.ambulance.services;

import com.savelife.ambulance.entities.Ambulance;
import com.savelife.ambulance.repositories.AmbulanceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AmbulanceService {

    private final AmbulanceRepository repository;
    
    private  AmbulanceService(AmbulanceRepository repository){
        this.repository = repository;
    }

    public Ambulance saveAmbulance(Ambulance ambulance){
        ambulance.getPhone().forEach(phone -> phone.setAmbulance(ambulance));
        return repository.save(ambulance);
    }

    public Ambulance findAmbulance(Long id){
        Optional<Ambulance> ambulance = repository.findById(id);
        return ambulance.orElseThrow(() -> new EntityNotFoundException("Ambulance Not Found!"));
    }

    public Page<Ambulance> listAllAmbulances(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Ambulance updateAmbulance(Ambulance updatedAmbulance, Long id){
        Ambulance ambulance = this.findAmbulance(id);
        updatedAmbulance.setId(ambulance.getId());
        for(int i = 0; i < updatedAmbulance.getPhone().size(); i++){
            updatedAmbulance.getPhone().get(i).setId(ambulance.getPhone().get(i).getId());
            updatedAmbulance.getPhone().get(i).setAmbulance(updatedAmbulance);
        }
        return repository.save(updatedAmbulance);
    }

    public void deleteAmbulance(Long id){
        Ambulance ambulance = this.findAmbulance(id);
        repository.delete(ambulance);
    }
}
