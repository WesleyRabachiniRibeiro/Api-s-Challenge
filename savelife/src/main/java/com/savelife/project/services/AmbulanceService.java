package com.savelife.project.services;

import com.savelife.project.entities.Ambulance;
import com.savelife.project.entities.Roles;
import com.savelife.project.entities.UserModel;
import com.savelife.project.repositories.AmbulanceRepository;
import com.savelife.project.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AmbulanceService {

    private final AmbulanceRepository repository;
    private final UserRepository userRepository;

    public AmbulanceService(AmbulanceRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Ambulance saveAmbulance(Ambulance ambulance){
        List<UserModel> userList = new ArrayList<>();
        ambulance.getPhones().forEach(
                phone -> {
                    UserModel user = userRepository.findByPhone(phone.getNumber()).orElse(new UserModel());
                    if (!(user.getRoles() == null)){
                        user.getRoles().forEach(role -> {
                            if(role.getName().equals(Roles.ROLE_AMBULANCE) || role.getName().equals(Roles.ROLE_ADMIN) & !userList.contains(user)){
                                userList.add(user);
                            }
                        });
                    }
                }
        );
        ambulance.setUsers(userList);
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
        List<UserModel> userList = new ArrayList<>();
        updatedAmbulance.getPhones().forEach(
                phone -> {
                    UserModel user = userRepository.findByPhone(phone.getNumber()).orElse(new UserModel());
                    if (!(user.getRoles() == null)){
                        user.getRoles().forEach(role -> {
                            if(role.getName().equals(Roles.ROLE_AMBULANCE)|| role.getName().equals(Roles.ROLE_ADMIN) & !userList.contains(user)){
                                userList.add(user);
                            }
                        });
                    }
                }
        );
        updatedAmbulance.setUsers(userList);
        updatedAmbulance.setId(ambulance.getId());
        return repository.save(updatedAmbulance);
    }

    public void deleteAmbulance(@PathVariable Long id){
        Ambulance ambulance = this.findAmbulance(id);
        repository.delete(ambulance);
    }
    
}
