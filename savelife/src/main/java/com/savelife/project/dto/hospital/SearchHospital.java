package com.savelife.project.dto.hospital;


import com.savelife.project.dto.ambulance.SearchAmbulance;
import com.savelife.project.dto.address.SearchAddress;
import com.savelife.project.dto.phone.SearchPhone;

import java.util.List;

public class SearchHospital {

    private String name;

    private SearchAddress address;

    private List<SearchPhone> phone;

    private List<SearchAmbulance> ambulances;

    public SearchHospital() {
    }

    public SearchHospital(String name, SearchAddress address, List<SearchPhone> phone, List<SearchAmbulance> ambulances) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.ambulances = ambulances;
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

    public List<SearchAmbulance> getAmbulances() {
        return ambulances;
    }

    public void setAmbulances(List<SearchAmbulance> ambulances) {
        this.ambulances = ambulances;
    }
}
