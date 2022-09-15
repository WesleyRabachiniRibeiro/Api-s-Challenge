package com.savelife.project.mappers;

import com.savelife.project.dto.ambulance.RegistryAmbulance;
import com.savelife.project.dto.ambulance.SearchAmbulance;
import com.savelife.project.dto.ambulance.SearchAmbulanceToRequest;
import com.savelife.project.entities.Ambulance;


import java.util.ArrayList;
import java.util.List;

public class AmbulanceMapper {

    public static Ambulance fromDTO(RegistryAmbulance ambulance){
        return new Ambulance(
                null,
                ambulance.getLicensePlate(),
                false,
                ambulance.getPhones(),
                new ArrayList<>(),
                null
        );
    }


    public static SearchAmbulance fromEntity(Ambulance ambulance){
        return new SearchAmbulance(
                ambulance.getLicensePlate(),
                ambulance.isStatus(),
                PhoneMapper.fromListEntity(ambulance.getPhones()),
                UserMapper.fromListEntity(ambulance.getUsers())
        );

    }

    public static SearchAmbulanceToRequest fromEntityRequest(Ambulance ambulance){
        return new SearchAmbulanceToRequest(
                ambulance.getLicensePlate(),
                PhoneMapper.fromListEntity(ambulance.getPhones())
        );
    }


    public static List<SearchAmbulance> fromListEntity(List<Ambulance> ambulances){
        List<SearchAmbulance> searchAmbulances = new ArrayList<SearchAmbulance>();
        ambulances.forEach(ambulance -> {
            searchAmbulances.add(new SearchAmbulance(
                    ambulance.getLicensePlate(),
                    ambulance.isStatus(),
                    PhoneMapper.fromListEntity(ambulance.getPhones()),
                    UserMapper.fromListEntity(ambulance.getUsers()))
            );
        });
        return searchAmbulances;
    }
}
