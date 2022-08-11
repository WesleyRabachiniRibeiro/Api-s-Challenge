package com.savelife.ambulance.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "T_SL_AMBULANCIA")
@SequenceGenerator(name = "SQ_T_AMBULANCIA", sequenceName = "SQ_T_AMBULANCIA", allocationSize = 1)
public class Ambulance {

    @Id
    @Column(name="CD_AMBULANCIA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_T_AMBULANCIA")
    private Long id;

    @Column(name = "DS_PLACA", length = 7)
    private String licensePlate;

    @OneToMany(mappedBy = "ambulance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phone;

    @Transient
    private List<Long> hospital;

    public Ambulance() {
    }

    public Ambulance(Long id, String licensePlate, List<Phone> phone, List<Long> hospital) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.phone = phone;
        this.hospital = hospital;
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

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }

    public List<Long> getHospital() {
        return hospital;
    }

    public void setHospital(List<Long> hospital) {
        this.hospital = hospital;
    }

}
