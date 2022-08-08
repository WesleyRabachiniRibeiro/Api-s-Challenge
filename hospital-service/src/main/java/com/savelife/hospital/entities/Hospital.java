package com.savelife.hospital.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "T_SL_HOSPITAL")
@SequenceGenerator(name = "SQ_T_HOSPITAL", sequenceName = "SQ_T_HOSPITAL", allocationSize = 1)
public class Hospital {

    @Id
    @Column(name = "CD_HOSPITAL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_T_HOSPITAL")
    private Long id;

    @Column(name = "NM_HOSPITAL")
    private String name;

    @OneToOne(mappedBy = "hospital",cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Phone> phone;

    public Hospital() {
    }

    public Hospital(Long id, String name, Address address, List<Phone> phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }
}
