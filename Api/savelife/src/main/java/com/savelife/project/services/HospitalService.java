package com.savelife.project.services;

import com.savelife.project.dto.hospital.RegistryHospital;
import com.savelife.project.entities.*;
import com.savelife.project.mappers.HospitalMapper;
import com.savelife.project.repositories.AmbulanceRepository;
import com.savelife.project.repositories.HospitalRepository;
import com.savelife.project.repositories.RoleRepository;
import com.savelife.project.repositories.UserRepository;
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
    private final UserRepository userRepository;


    public HospitalService(HospitalRepository repository, AmbulanceRepository ambulanceRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.repository = repository;
        this.ambulanceRepository = ambulanceRepository;
        this.userRepository = userRepository;
    }

    public Hospital saveHospital(RegistryHospital dto){
        List<Ambulance> ambulances = new ArrayList<>();
        List<UserModel> userList = new ArrayList<>();
        dto.getAmbulances().forEach( id ->
                ambulances.add(ambulanceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ambulance not Found!")))
        );
        Hospital hospital = HospitalMapper.fromDTO(dto);
        dto.getEmail().forEach(
                email -> {
                    UserModel user = userRepository.findByEmail(email).orElse(new UserModel());
                    if (!(user.getRoles() == null)){
                        user.getRoles().forEach(role -> {
                            if(role.getName().equals(Roles.ROLE_HOSPITAL)){
                                userList.add(user);
                            }
                        });
                    }
                }
        );
        hospital.setUsers(userList);
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
        List<UserModel> userList = new ArrayList<>();
        dto.getAmbulances().forEach( ambulanceId ->
                ambulances.add(ambulanceRepository.findById(ambulanceId).orElseThrow(() -> new EntityNotFoundException("Ambulance not Found!")))
        );
        dto.getEmail().forEach(
                email -> {
                    UserModel user = userRepository.findByEmail(email).orElse(new UserModel());
                    if (!(user.getRoles() == null)){
                        user.getRoles().forEach(role -> {
                            if(role.getName().equals(Roles.ROLE_HOSPITAL) || role.getName().equals(Roles.ROLE_ADMIN) & !userList.contains(user)){
                                userList.add(user);
                            }
                        });
                    }
                }
        );
        hospital.setUsers(userList);
        updatedHospital.setId(hospital.getId());
        updatedHospital.setAmbulances(ambulances);
        return repository.save(updatedHospital);
    }

    public void deleteHospital(Long id){
        Hospital hospital = this.findHospital(id);
        repository.delete(hospital);
    }

}
