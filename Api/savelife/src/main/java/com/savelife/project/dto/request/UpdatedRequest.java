package com.savelife.project.dto.request;

public class UpdatedRequest {

    private String description;

    public UpdatedRequest() {
    }

    public UpdatedRequest(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
