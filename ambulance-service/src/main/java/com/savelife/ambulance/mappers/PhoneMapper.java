package com.savelife.ambulance.mappers;

import com.savelife.ambulance.dto.phone.PhoneDTO;
import com.savelife.ambulance.entities.Phone;
import java.util.ArrayList;
import java.util.List;

public class PhoneMapper {

    public static PhoneDTO fromEntity(Phone phone){
        return new PhoneDTO(phone.getNumber());
    }

    public static List<PhoneDTO> fromListEntity(List<Phone> phones){
        List<PhoneDTO> searchPhones = new ArrayList<PhoneDTO>();
        phones.forEach(phone -> {
            searchPhones.add(new PhoneDTO(phone.getNumber()));
        });
        return searchPhones;
    }
}
