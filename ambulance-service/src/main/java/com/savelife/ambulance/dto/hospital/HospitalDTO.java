package com.savelife.ambulance.dto.hospital;


import com.savelife.ambulance.dto.address.AddressDTO;
import com.savelife.ambulance.dto.phone.PhoneDTO;

import java.util.List;

public class HospitalDTO {

    private String name;

    private AddressDTO address;

    private List<PhoneDTO> phone;


    public HospitalDTO() {
    }

    public HospitalDTO(String name, AddressDTO address, List<PhoneDTO> phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<PhoneDTO> getPhone() {
        return phone;
    }

    public void setPhone(List<PhoneDTO> phone) {
        this.phone = phone;
    }

}
