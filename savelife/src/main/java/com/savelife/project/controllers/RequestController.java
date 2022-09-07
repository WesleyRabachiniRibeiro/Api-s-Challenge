package com.savelife.project.controllers;

import com.savelife.project.dto.request.RegistryRequest;
import com.savelife.project.dto.request.SearchRequest;
import com.savelife.project.dto.request.UpdatedRequest;
import com.savelife.project.entities.Request;
import com.savelife.project.mappers.RequestMapper;
import com.savelife.project.services.RequestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/request")
@Api(value = "API REST Pedidos")
@CrossOrigin(origins = "*")
public class RequestController {

    private final RequestService service;

    private RequestController(RequestService service){
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "Create new Requests")
    public ResponseEntity<SearchRequest> saveRequest(@RequestBody RegistryRequest dto){
        Request request = service.saveRequest(dto);
        return new ResponseEntity<SearchRequest>(RequestMapper.fromEntity(request), HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value = "Return all registered requests")
    public ResponseEntity<Page<SearchRequest>> findAllRequests(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(service.listAllRequests(pageable).map(RequestMapper::fromEntity));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Return a Request to ID")
    public ResponseEntity<SearchRequest> searchRequest(@PathVariable Long id){
        try{
            Request request = service.findRequest(id);
            return ResponseEntity.ok(RequestMapper.fromEntity(request));
        }catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Update Request to ID")
    public ResponseEntity<SearchRequest> updateRequest(@RequestBody UpdatedRequest dto, @PathVariable Long id){
        try{
            Request request = service.updateRequest(dto, id);
            return ResponseEntity.ok(RequestMapper.fromEntity(request));
        }catch (RuntimeException ex){
            ex.getStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete Request to ID")
    public ResponseEntity<SearchRequest> deleteRequest(@PathVariable Long id){
        try{
            service.deleteRequest(id);
            return ResponseEntity.ok().build();
        }catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }

}
