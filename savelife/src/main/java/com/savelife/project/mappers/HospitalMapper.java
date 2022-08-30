package com.savelife.project.mappers;

import com.savelife.project.dto.hospital.RegistryHospital;
import com.savelife.project.dto.hospital.SearchHospital;
import com.savelife.project.entities.Hospital;

public class HospitalMapper {

    public static Hospital fromDTO(RegistryHospital hospital){
        return new Hospital(
                null,
                hospital.getName(),
                hospital.getAddress(),
                hospital.getPhones(),
                null
        );
    }

    public static SearchHospital fromEntity(Hospital hospital){
        return new SearchHospital(
                hospital.getName(),
                AddressMapper.fromEntity(hospital.getAddress()),
                PhoneMapper.fromListEntity(hospital.getPhones()),
                AmbulanceMapper.fromListEntity(hospital.getAmbulances())
        );
    }

}
