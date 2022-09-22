package com.savelife.project.dto.user;

import javax.validation.constraints.Email;

public class RegistryEmail {

    @Email
    private String email;

    public RegistryEmail() {
    }

    public RegistryEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
