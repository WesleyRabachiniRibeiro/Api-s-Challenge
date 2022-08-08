package com.savelife.hospital.dto.address;

public class SearchAddress {

    private String street;

    private String district;

    private String cep;

    private String state;

    private String city;

    public SearchAddress(String street, String district, String cep, String state, String city) {
        this.street = street;
        this.district = district;
        this.cep = cep;
        this.state = state;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
