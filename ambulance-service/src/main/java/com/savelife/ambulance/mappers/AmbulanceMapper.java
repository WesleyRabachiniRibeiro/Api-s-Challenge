package com.savelife.ambulance.mappers;

import com.savelife.ambulance.client.HospitalFeing;
import com.savelife.ambulance.dto.ambulance.RegistryAmbulance;
import com.savelife.ambulance.dto.ambulance.SearchAmbulance;
import com.savelife.ambulance.dto.ambulance.SearchAmbulanceAndHospital;
import com.savelife.ambulance.dto.hospital.HospitalDTO;
import com.savelife.ambulance.entities.Ambulance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AmbulanceMapper {

    @Autowired
    private HospitalFeing hospitalFeing;


    public static Ambulance fromDTO(RegistryAmbulance ambulance){

        return new Ambulance(
                null,
                ambulance.getLicensePlate(),
                ambulance.getPhone(),
                ambulance.getHospital()
        );
    }

    public static SearchAmbulanceAndHospital fromAmbulanceAndHospitalEntity(Ambulance ambulance, List<HospitalDTO> hospital){
        return new SearchAmbulanceAndHospital(
                ambulance.getLicensePlate(),
                PhoneMapper.fromListEntity(ambulance.getPhone()),
                hospital
        );
    }
    public static SearchAmbulance fromEntity(Ambulance ambulance){
        return new SearchAmbulance(
            ambulance.getLicensePlate(),
            PhoneMapper.fromListEntity(ambulance.getPhone())
        );
    }
    
}
