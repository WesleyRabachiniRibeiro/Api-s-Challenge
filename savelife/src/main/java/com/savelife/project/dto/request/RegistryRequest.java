package com.savelife.project.dto.request;

import com.savelife.project.entities.Urgency;


public class RegistryRequest {

    private Long user;

    private String hospital;

    private Urgency urgent;

    private String description;

    public RegistryRequest(Long user, String hospital, Urgency urgent, String description) {
        this.user = user;
        this.hospital = hospital;
        this.urgent = urgent;
        this.description = description;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public Urgency getUrgent() {
        return urgent;
    }

    public void setUrgent(Urgency urgent) {
        this.urgent = urgent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
