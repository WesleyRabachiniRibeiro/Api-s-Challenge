package com.savelife.project.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_SL_AMBULANCIA")
@SequenceGenerator(name = "SQ_T_AMBULANCIA", sequenceName = "SQ_T_AMBULANCIA", allocationSize = 1)
public class Ambulance {

    @Id
    @Column(name = "CD_AMBULANCIA", length = 3, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_T_AMBULANCIA")
    private Long id;

    @Column(name = "DS_PLACA", length = 7, nullable = false)
    private String licensePlate;

    @Column(name = "ST_AMBULANCIA", length = 1, nullable = false)
    private boolean status;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "T_SL_AMBULANCIA_TELEFONE",
            joinColumns = @JoinColumn(name = "CD_AMBULANCIA",
                    foreignKey = @ForeignKey(name = "FK_SL_AMBULANCIA_TELEFONE")),
            inverseJoinColumns = @JoinColumn(name = "CD_TELEFONE",
                    foreignKey = @ForeignKey(name = "FK_SL_TELEFONE_AMBULANCIA")))
    private List<Phone> phones;

    @ManyToMany(mappedBy = "ambulances", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Hospital> hospitals;

    @OneToOne(mappedBy = "ambulance")
    private Request request;

    @ManyToMany
    @JoinTable(
            name = "T_SL_USUARIO_AMBULANCIA",
            joinColumns = @JoinColumn(name = "CD_USUARIO"),
            inverseJoinColumns = @JoinColumn(name = "CD_AMBULANCE"))
    private List<UserModel> users;

    public Ambulance() {
    }

    public Ambulance(Long id, String licensePlate, boolean status, List<Phone> phones, List<Hospital> hospitals, Request request) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.status = status;
        this.phones = phones;
        this.hospitals = hospitals;
        this.request = request;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(List<Hospital> hospitals) {
        this.hospitals = hospitals;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }
}
