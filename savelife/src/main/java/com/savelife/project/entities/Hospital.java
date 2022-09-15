package com.savelife.project.entities;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "T_SL_HOSPITAL")
@SequenceGenerator(name = "SQ_T_HOSPITAL", sequenceName = "SQ_T_HOSPITAL", allocationSize = 1)
public class Hospital{

    @Id
    @Column(name = "CD_HOSPITAL", length = 3, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_T_HOSPITAL")
    private Long id;

    @Column(name = "NM_HOSPITAL", length = 120, nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CD_ENDERECO", referencedColumnName = "CD_ENDERECO", foreignKey = @ForeignKey(name = "FK_SL_HOSPITAL_ENDERECO"), nullable = false)
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "T_SL_HOSPITAL_TELEFONE",
            joinColumns = @JoinColumn(name = "CD_HOSPITAL", foreignKey = @ForeignKey(name = "FK_SL_HOSPITAL_TELEFONE")),
            inverseJoinColumns = @JoinColumn(name = "CD_TELEFONE", foreignKey = @ForeignKey(name = "FK_SL_TELEFONE_HOSPITAL")))
    private List<Phone> phones;

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE,CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "T_SL_AMBULANCIA_HOSPITAL",
            joinColumns = @JoinColumn(name = "CD_HOSPITAL", foreignKey = @ForeignKey(name = "FK_SL_HOSPITAL_AMBULANCIA")),
            inverseJoinColumns = @JoinColumn(name = "CD_AMBULANCIA", foreignKey = @ForeignKey(name = "FK_SL_AMBULANCIA_HOSPITAL")))
    private List<Ambulance> ambulances;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.REFRESH)
    private List<Request> requests;

    @ManyToMany
    @JoinTable(
            name = "T_SL_USUARIO_HOSPITAL",
            joinColumns = @JoinColumn(name = "CD_USUARIO"),
            inverseJoinColumns = @JoinColumn(name = "CD_HOSPITAL"))
    private List<UserModel> users;

    public Hospital() {
    }

    public Hospital(Long id, String name, Address address, List<Phone> phones, List<Ambulance> ambulances, List<Request> requests, List<UserModel> users) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phones = phones;
        this.ambulances = ambulances;
        this.requests = requests;
        this.users = users;
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

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }
}
