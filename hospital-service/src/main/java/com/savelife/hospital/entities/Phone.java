package com.savelife.hospital.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_SL_TELEFONE_HOSPITAL")
@SequenceGenerator(name = "SQ_T_TELEFONE_HOSPITAL", sequenceName = "SQ_T_TELEFONE_HOSPITAL", allocationSize = 1)
public class Phone {

    @Id
    @Column(name = "CD_TELEFONE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_T_TELEFONE_HOSPITAL")
    private Long id;

    @Column(name = "NR_TELEFONE")
    private String number;

    @ManyToOne
    @JoinColumn(name = "CD_HOSPITAL", referencedColumnName = "CD_HOSPITAL")
    private Hospital hospital;

    public Phone() {
    }

    public Phone(Long id, String number, Hospital hospital) {
        this.id = id;
        this.number = number;
        this.hospital = hospital;
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

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
