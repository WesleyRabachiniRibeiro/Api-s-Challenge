package com.savelife.hospital.controllers;

import com.savelife.hospital.mappers.HospitalMapper;
import com.savelife.hospital.dto.hospital.RegistryHospital;
import com.savelife.hospital.dto.hospital.SearchHospital;
import com.savelife.hospital.entities.Hospital;
import com.savelife.hospital.services.HospitalService;
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

@RestController
@RequestMapping("/v1/hospital")
@Api(value = "API REST Hospitais")
@CrossOrigin(origins = "*")
public class HospitalController {

    private final HospitalService service;

    public HospitalController(HospitalService service) {
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "Create new Hospitals")
    public ResponseEntity<SearchHospital> saveHospital(@RequestBody RegistryHospital dto){
        Hospital hospital = service.saveHospital(HospitalMapper.fromDTO(dto));
        return new ResponseEntity<SearchHospital>(HospitalMapper.fromEntity(hospital), HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value = "Return all registered hospitals")
    public ResponseEntity<Page<SearchHospital>> findAllHospital(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(service.listAllHospitals(pageable).map(HospitalMapper::fromEntity));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Return a Hospital to ID")
    public ResponseEntity<SearchHospital> searchHospital(@PathVariable Long id){
        try{
            Hospital hospital = service.findHospital(id);
            return ResponseEntity.ok(HospitalMapper.fromEntity(hospital));
        }catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Update Hospital to ID")
    public ResponseEntity<SearchHospital> updateUser(@RequestBody RegistryHospital dto, @PathVariable Long id){
        try{
            Hospital hospital = service.updateHospital(HospitalMapper.fromDTO(dto), id);
            return ResponseEntity.ok(HospitalMapper.fromEntity(hospital));
        }catch (RuntimeException ex){
            ex.getStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete Hospital to ID")
    public ResponseEntity<SearchHospital> deleteHospital(@PathVariable Long id){
        try{
            service.deleteHospital(id);
            return ResponseEntity.ok().build();
        }catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }
}

