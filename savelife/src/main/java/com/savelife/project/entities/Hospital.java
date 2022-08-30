package com.savelife.project.entities;

import javax.persistence.*;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CD_ENDERECO", referencedColumnName = "CD_ENDERECO")
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "T_SL_HOSPITAL_TELEFONE",
            joinColumns = @JoinColumn(name = "CD_HOSPITAL"),
            inverseJoinColumns = @JoinColumn(name = "CD_TELEFONE"))
    private List<Phone> phones;

    @ManyToMany
    @JoinTable(
            name = "T_SL_AMBULANCIA_HOSPITAL",
            joinColumns = @JoinColumn(name = "CD_HOSPITAL"),
            inverseJoinColumns = @JoinColumn(name = "CD_AMBULANCIA"))
    private List<Ambulance> ambulances;

    @OneToMany(mappedBy = "hospital")
    private List<Request> requests;

    public Hospital() {
    }

    public Hospital(Long id, String name, Address address, List<Phone> phones, List<Ambulance> ambulances) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phones = phones;
        this.ambulances = ambulances;
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

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Ambulance> getAmbulances() {
        return ambulances;
    }

    public void setAmbulances(List<Ambulance> ambulances) {
        this.ambulances = ambulances;
    }
}
