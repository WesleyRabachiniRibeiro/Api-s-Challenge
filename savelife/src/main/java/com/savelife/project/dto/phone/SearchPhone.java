package com.savelife.project.dto.phone;

import javax.validation.constraints.NotBlank;

public class SearchPhone {

    @NotBlank
    private String number;

    public SearchPhone(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
