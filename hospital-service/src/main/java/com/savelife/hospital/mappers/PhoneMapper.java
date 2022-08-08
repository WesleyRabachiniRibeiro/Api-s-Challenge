package com.savelife.hospital.mappers;

import com.savelife.hospital.dto.phone.SearchPhone;
import com.savelife.hospital.entities.Phone;

import java.util.ArrayList;
import java.util.List;

public class PhoneMapper {

    public static SearchPhone fromEntity(Phone phone){
        return new SearchPhone(phone.getNumber());
    }

    public static List<SearchPhone> fromListEntity(List<Phone> phones){
        List<SearchPhone> searchPhones = new ArrayList<SearchPhone>();
        phones.forEach(phone -> {
            searchPhones.add(new SearchPhone(phone.getNumber()));
        });
        return searchPhones;
    }
}
