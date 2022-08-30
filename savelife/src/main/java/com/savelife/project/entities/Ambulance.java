package com.savelife.project.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_SL_AMBULANCIA")
@SequenceGenerator(name = "SQ_T_AMBULANCIA", sequenceName = "SQ_T_AMBULANCIA", allocationSize = 1)
public class Ambulance {

    @Id
    @Column(name = "CD_AMBULANCIA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_T_AMBULANCIA")
    private Long id;

    @Column(name = "DS_PLACA")
    private String licensePlate;

    @Column(name = "ST_AMBULANCIA")
    private boolean status;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "T_SL_AMBULANCIA_TELEFONE",
            joinColumns = @JoinColumn(name = "CD_AMBULANCIA"),
            inverseJoinColumns = @JoinColumn(name = "CD_TELEFONE"))
    private List<Phone> phones;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Hospital> hospitals;

    @OneToOne(mappedBy = "ambulance")
    private Request request;

    public Ambulance() {
    }

    public Ambulance(Long id, String licensePlate, boolean status, List<Phone> phones, List<Hospital> hospitals) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.status = status;
        this.phones = phones;
        this.hospitals = hospitals;
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
}
