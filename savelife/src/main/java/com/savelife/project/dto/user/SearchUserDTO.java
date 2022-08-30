package com.savelife.project.dto.user;

import java.util.List;

public class SearchUserDTO {

    private String name;

    private Integer age;

    private String phone;

    private String email;

    private byte[] picture;

    private String healthPlan;

    private String susCard;

    private String cpf;

    public SearchUserDTO() {
    }

    public SearchUserDTO( String name, Integer age, String phone, String email, byte[] picture, String healthPlan, String susCard, String cpf) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.picture = picture;
        this.healthPlan = healthPlan;
        this.susCard = susCard;
        this.cpf = cpf;
    }

    public SearchUserDTO(String name, Integer age, String phone, String email, String susCard,String cpf) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.susCard = susCard;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getHealthPlan() {
        return healthPlan;
    }

    public void setHealthPlan(String healthPlan) {
        this.healthPlan = healthPlan;
    }

    public String getSusCard() {
        return susCard;
    }

    public void setSusCard(String susCard) {
        this.susCard = susCard;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
