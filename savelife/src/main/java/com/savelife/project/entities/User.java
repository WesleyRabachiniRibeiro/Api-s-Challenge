package com.savelife.project.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_SL_USUARIO")
@SequenceGenerator(name = "SQ_T_USUARIO", sequenceName = "SQ_T_USUARIO", allocationSize = 1)
public class User {

    @Id
    @Column(name = "CD_USUARIO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_T_USUARIO")
    private Long id;

    @Column(name = "NM_NOME")
    private String name;

    @Column(name = "NR_IDADE")
    private Integer age;

    @Column(name = "NR_TELEFONE")
    private String phone;

    @Column(name = "DS_EMAIL")
    private String email;

    @Column(name = "DS_SENHA")
    private String password;

    @Column(name = "IMG_FOTO")
    private byte[] picture;

    @Column(name = "DS_PLANO_DE_SAUDE")
    private String healthPlan;

    @Column(name = "DS_CARTAO_SUS")
    private String susCard;

    @Column(name = "DS_CPF")
    private String cpf;

    @OneToMany(mappedBy = "user")
    private List<Request> request;

    public User(Long id, String name, Integer age, String phone, String email, String password ,byte[] picture, String healthPlan, String susCard, String cpf) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.picture = picture;
        this.healthPlan = healthPlan;
        this.susCard = susCard;
        this.cpf = cpf;
    }

    public User(Long id, String name, Integer age, String phone, String email, String password, String susCard, String cpf) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.susCard = susCard;
        this.cpf = cpf;
    }

    public User() {
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

    public String getPassword() {return password; }

    public void setPassword(String password) { this.password = password; }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }
}
