package com.savelife.hospital.mappers;

import com.savelife.hospital.dto.address.SearchAddress;
import com.savelife.hospital.entities.Address;

public class AddressMapper {
    public static SearchAddress fromEntity(Address address){
        return new SearchAddress(address.getStreet(), address.getDistrict(), address.getCep(), address.getState(), address.getCity());
    }

}
