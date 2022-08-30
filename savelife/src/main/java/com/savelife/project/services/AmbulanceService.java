package com.savelife.project.services;

import com.savelife.project.entities.Ambulance;
import com.savelife.project.repositories.AmbulanceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AmbulanceService {

    private final AmbulanceRepository repository;

    private  AmbulanceService(AmbulanceRepository repository){
        this.repository = repository;
    }

    public Ambulance saveAmbulance(Ambulance ambulance){
        return repository.save(ambulance);
    }

    public Ambulance findAmbulance(@PathVariable Long id){
        Optional<Ambulance> ambulance = repository.findById(id);
        return ambulance.orElseThrow(() -> new EntityNotFoundException("Ambulance Not Found!"));
    }

    public Page<Ambulance> listAllAmbulances(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Ambulance updateAmbulance(Ambulance updatedAmbulance, Long id){
        Ambulance ambulance = this.findAmbulance(id);
        updatedAmbulance.setId(ambulance.getId());
        return repository.save(updatedAmbulance);
    }

    public void deleteAmbulance(@PathVariable Long id){
        Ambulance ambulance = this.findAmbulance(id);
        repository.delete(ambulance);
    }
    
}
