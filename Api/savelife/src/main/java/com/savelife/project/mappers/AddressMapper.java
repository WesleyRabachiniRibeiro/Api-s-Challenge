package com.savelife.project.mappers;

import com.savelife.project.dto.address.SearchAddress;
import com.savelife.project.entities.Address;

public class AddressMapper {
    public static SearchAddress fromEntity(Address address){
        return new SearchAddress(address.getStreet(), address.getDistrict(), address.getCep(), address.getState(), address.getCity());
    }

}
