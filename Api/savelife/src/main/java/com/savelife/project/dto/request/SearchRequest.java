package com.savelife.project.dto.request;

import com.savelife.project.dto.ambulance.SearchAmbulance;
import com.savelife.project.dto.ambulance.SearchAmbulanceToRequest;
import com.savelife.project.dto.user.SearchUserDTO;
import com.savelife.project.entities.Urgency;

public class SearchRequest {

    private SearchUserDTO user;

    private String hospital;

    private SearchAmbulanceToRequest ambulance;

    private String description;

    private Urgency urgent;

    public SearchRequest(SearchUserDTO user, String hospital, SearchAmbulanceToRequest ambulance, String description, Urgency urgent) {
        this.user = user;
        this.hospital = hospital;
        this.ambulance = ambulance;
        this.description = description;
        this.urgent = urgent;
    }

    public SearchUserDTO getUser() {
        return user;
    }

    public void setUser(SearchUserDTO user) {
        this.user = user;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public SearchAmbulanceToRequest getAmbulance() {
        return ambulance;
    }

    public void setAmbulance(SearchAmbulanceToRequest ambulance) {
        this.ambulance = ambulance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Urgency getUrgent() {
        return urgent;
    }

    public void setUrgent(Urgency urgent) {
        this.urgent = urgent;
    }
}
