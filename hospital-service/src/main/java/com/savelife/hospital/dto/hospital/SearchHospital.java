package com.savelife.hospital.dto.hospital;


import com.savelife.hospital.dto.address.SearchAddress;
import com.savelife.hospital.dto.phone.SearchPhone;

import java.util.List;

public class SearchHospital {

    private String name;

    private SearchAddress address;

    private List<SearchPhone> phone;

    public SearchHospital() {
    }

    public SearchHospital(String name, SearchAddress address, List<SearchPhone> phone) {
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

    public SearchAddress getAddress() {
        return address;
    }

    public void setAddress(SearchAddress address) {
        this.address = address;
    }

    public List<SearchPhone> getPhone() {
        return phone;
    }

    public void setPhone(List<SearchPhone> phone) {
        this.phone = phone;
    }
}
