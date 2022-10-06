package com.savelife.project.dto.user;

import com.savelife.project.entities.Role;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.cdi.Eager;

import java.util.List;

public class SearchUserEmail {

    private Long id;

    private String name;

    private Integer age;

    private String phone;

    private String email;

    private byte[] picture;

    private String healthPlan;

    private String susCard;

    private String cpf;

    private List<String> role;

    public SearchUserEmail(Long id, String name, Integer age, String phone, String email, byte[] picture, String healthPlan, String susCard, String cpf, List<String> role) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.picture = picture;
        this.healthPlan = healthPlan;
        this.susCard = susCard;
        this.cpf = cpf;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}
