package com.savelife.hospital.mappers;

import com.savelife.hospital.dto.hospital.RegistryHospital;
import com.savelife.hospital.dto.hospital.SearchHospital;
import com.savelife.hospital.entities.Hospital;

public class HospitalMapper {

    public static Hospital fromDTO(RegistryHospital hospital){
        return new Hospital(
                null,
                hospital.getName(),
                hospital.getAddress()
        );
    }

    public static SearchHospital fromEntity(Hospital hospital){
        return new SearchHospital(
                hospital.getId(),
                hospital.getName(),
                hospital.getAddress()
        );
    }

}
