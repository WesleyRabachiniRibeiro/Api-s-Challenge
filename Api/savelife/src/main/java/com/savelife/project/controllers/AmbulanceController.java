package com.savelife.project.controllers;

import com.savelife.project.dto.ambulance.RegistryAmbulance;
import com.savelife.project.dto.ambulance.SearchAmbulance;
import com.savelife.project.entities.Ambulance;
import com.savelife.project.mappers.AmbulanceMapper;
import com.savelife.project.services.AmbulanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/ambulance")
@Api(value = "API REST Ambulância")
@CrossOrigin(origins = "*")
public class AmbulanceController {

    private final AmbulanceService service;

    public AmbulanceController(AmbulanceService service){
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "Create new Ambulances")
    public ResponseEntity<SearchAmbulance> saveAmbulance(@RequestBody RegistryAmbulance dto){
        try {
            Ambulance ambulance = service.saveAmbulance(AmbulanceMapper.fromDTO(dto));
            return new ResponseEntity<SearchAmbulance>(AmbulanceMapper.fromEntity(ambulance), HttpStatus.CREATED);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    @ApiOperation(value = "Return all registered ambulances")
    public ResponseEntity<Page<SearchAmbulance>> findAllAmbulances(@PageableDefault Pageable pageable){
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
    public ResponseEntity<SearchAmbulance> updateAmbulance(@RequestBody RegistryAmbulance dto, @PathVariable Long id){
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
            return ResponseEntity.noContent().build();
        }catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }
}
