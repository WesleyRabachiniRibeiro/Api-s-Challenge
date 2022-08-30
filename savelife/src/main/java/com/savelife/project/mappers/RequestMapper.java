package com.savelife.project.mappers;
import com.savelife.project.dto.request.RegistryRequest;
import com.savelife.project.dto.request.SearchRequest;
import com.savelife.project.dto.request.UpdatedRequest;
import com.savelife.project.entities.Request;

import java.time.LocalDate;

public class RequestMapper {

    public static Request fromDTO(RegistryRequest request){
        return new Request(
                null,
                null,
                null,
                null,
                LocalDate.now(),
                request.getUrgent(),
                request.getDescription()
        );
    }
    public static SearchRequest fromEntity(Request request){
        return new SearchRequest(
                UserMapper.fromEntity(request.getUser()),
                request.getHospital().getName(),
                AmbulanceMapper.fromEntityRequest(request.getAmbulance()),
                request.getDescription(),
                request.getUrgency()
        );
    }

    public static UpdatedRequest fromEntityRequest(Request request){
        return new UpdatedRequest(
                request.getDescription()
        );
    }

}
