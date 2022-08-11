package com.savelife.ambulance.controllers;

import com.savelife.ambulance.client.HospitalFeing;
import com.savelife.ambulance.dto.ambulance.RegistryAmbulance;
import com.savelife.ambulance.dto.ambulance.SearchAmbulance;
import com.savelife.ambulance.dto.ambulance.SearchAmbulanceAndHospital;
import com.savelife.ambulance.dto.hospital.HospitalDTO;
import com.savelife.ambulance.entities.Ambulance;
import com.savelife.ambulance.mappers.AmbulanceMapper;
import com.savelife.ambulance.services.AmbulanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/ambulance")
@Api(value = "API REST Ambulancias")
@CrossOrigin(origins = "*")
public class AmbulanceController {

    private final AmbulanceService service;
    private final HospitalFeing hospitalFeing;

    public AmbulanceController(AmbulanceService service, HospitalFeing hospitalFeing) {
        this.service = service;
        this.hospitalFeing = hospitalFeing;
    }

    @PostMapping
    @ApiOperation(value = "Create new Ambulance")
    public ResponseEntity<SearchAmbulanceAndHospital> saveAmbulance(@RequestBody RegistryAmbulance dto){
        Ambulance ambulance = service.saveAmbulance(AmbulanceMapper.fromDTO(dto));
        List<HospitalDTO> hospitalList = new ArrayList<HospitalDTO>();
        ambulance.getHospital().forEach(hospital -> {
            hospitalList.add(hospitalFeing.searchHospital(hospital));
        });
        return new ResponseEntity<SearchAmbulanceAndHospital>(AmbulanceMapper.fromAmbulanceAndHospitalEntity(ambulance, hospitalList), HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value = "Return all registered Ambulances")
    public ResponseEntity<Page<SearchAmbulance>> findAllAmbulance(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(service.listAllAmbulances(pageable).map(AmbulanceMapper::fromEntity));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Return a Ambulance to ID")
    public ResponseEntity<SearchAmbulance> searchAmbulance(@PathVariable Long id){
        try{
            Ambulance ambulance = service.findAmbulance(id);
            return ResponseEntity.ok(AmbulanceMapper.fromEntity(ambulance));
        }catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Update Ambulance to ID")
    public ResponseEntity<SearchAmbulance> updateUser(@RequestBody RegistryAmbulance dto, @PathVariable Long id){
        try{
            Ambulance ambulance = service.updateAmbulance(AmbulanceMapper.fromDTO(dto), id);
            return ResponseEntity.ok(AmbulanceMapper.fromEntity(ambulance));
        }catch (RuntimeException ex){
            ex.getStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete Ambulance to ID")
    public ResponseEntity<SearchAmbulance> deleteAmbulance(@PathVariable Long id){
        try{
            service.deleteAmbulance(id);
            return ResponseEntity.ok().build();
        }catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }
}
