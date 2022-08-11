package com.savelife.ambulance.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_SL_TELEFONE_AMBULANCIA")
@SequenceGenerator(name = "SQ_T_TELEFONE_AMBULANCIA", sequenceName = "SQ_T_TELEFONE_AMBULANCIA", allocationSize = 1)
public class Phone {

    @Id
    @Column(name = "CD_TELEFONE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_T_TELEFONE_AMBULANCIA")
    private Long id;

    @Column(name = "NR_TELEFONE")
    private String number;

    @ManyToOne
    @JoinColumn(name = "CD_AMBULANCIA")
    private Ambulance ambulance;

    public Phone() {
    }

    public Phone(Long id, String number, Ambulance ambulance) {
        this.id = id;
        this.number = number;
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

    public Ambulance getAmbulance() {
        return ambulance;
    }

    public void setAmbulance(Ambulance ambulance) {
        this.ambulance = ambulance;
    }
}
