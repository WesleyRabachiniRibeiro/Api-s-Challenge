package com.savelife.project.services;

import com.savelife.project.dto.request.RegistryRequest;
import com.savelife.project.dto.request.UpdatedRequest;
import com.savelife.project.entities.Ambulance;
import com.savelife.project.entities.Hospital;
import com.savelife.project.entities.Request;
import com.savelife.project.mappers.RequestMapper;
import com.savelife.project.repositories.AmbulanceRepository;
import com.savelife.project.repositories.HospitalRepository;
import com.savelife.project.repositories.RequestRepository;
import com.savelife.project.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;

@Service
public class RequestService {
    private final RequestRepository repository;

    private final AmbulanceRepository ambulanceRepository;

    private final HospitalRepository hospitalRepository;

    private final UserRepository userRepository;

    public RequestService(RequestRepository repository, AmbulanceRepository ambulanceRepository, HospitalRepository hospitalRepository, UserRepository userRepository) {
        this.repository = repository;
        this.ambulanceRepository = ambulanceRepository;
        this.hospitalRepository = hospitalRepository;
        this.userRepository = userRepository;
    }

    public Request saveRequest(RegistryRequest dto){
        Request request = RequestMapper.fromDTO(dto);
        request.setUser(userRepository.findById(dto.getUser()).orElseThrow(
                () -> new EntityNotFoundException("User Not Found!")));
        Hospital hospital = hospitalRepository.findByAddressStreet(dto.getHospital());
        request.setHospital(hospital);
        for (Ambulance ambulance : hospital.getAmbulances()){
            if(!ambulance.isStatus()){
                ambulance.setStatus(true);
                request.setAmbulance(ambulance);
                break;
            }
        }
        return repository.save(request);
    }

    public Request findRequest(@PathVariable Long id){
        Request request = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Request Not Found"));
        return request;
    }

    public Page<Request> listAllRequests(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Request updateRequest(UpdatedRequest updatedRequest, Long id){
        Request request = this.findRequest(id);
        request.setDescription(updatedRequest.getDescription());
        return repository.save(request);
    }

    public void deleteRequest(@PathVariable Long id){
        Request request = this.findRequest(id);
        repository.delete(request);
    }
}
