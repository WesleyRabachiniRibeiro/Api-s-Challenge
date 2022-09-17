package com.savelife.project.dto.ambulance;

import com.savelife.project.dto.phone.SearchPhone;
import com.savelife.project.dto.user.SearchUserDTO;

import java.util.List;

public class SearchAmbulance {

    private String licensePlate;

    private boolean status;

    private List<SearchPhone> phones;

    private List<SearchUserDTO> users;

    public SearchAmbulance(String licensePlate, boolean status, List<SearchPhone> phones, List<SearchUserDTO> users) {
        this.licensePlate = licensePlate;
        this.status = status;
        this.phones = phones;
        this.users = users;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<SearchPhone> getPhones() {
        return phones;
    }

    public void setPhones(List<SearchPhone> phones) {
        this.phones = phones;
    }

    public List<SearchUserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<SearchUserDTO> users) {
        this.users = users;
    }
}
