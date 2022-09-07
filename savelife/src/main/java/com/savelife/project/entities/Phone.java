package com.savelife.project.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_SL_TELEFONE")
@SequenceGenerator(name = "SQ_T_TELEFONE", sequenceName = "SQ_T_TELEFONE", allocationSize = 1)
public class Phone {

    @Id
    @Column(name = "CD_TELEFONE", length = 3, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_T_TELEFONE")
    private Long id;

    @Column(name = "NR_TELEFONE", length = 11, nullable = false)
    private String number;


    @ManyToMany(mappedBy = "phones")
    private List<Hospital> hospital;

    @ManyToMany(mappedBy = "phones")
    private List<Ambulance> ambulance;

    public Phone() {
    }


    public Phone(Long id, String number, List<Hospital> hospital, List<Ambulance> ambulance) {
        this.id = id;
        this.number = number;
        this.hospital = hospital;
        this.ambulance = ambulance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Hospital> getHospital() {
        return hospital;
    }

    public void setHospital(List<Hospital> hospital) {
        this.hospital = hospital;
    }

    public List<Ambulance> getAmbulance() {
        return ambulance;
    }

    public void setAmbulance(List<Ambulance> ambulance) {
        this.ambulance = ambulance;
    }
}
