package com.savelife.project.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "T_SL_PEDIDO")
@SequenceGenerator(name = "SQ_T_PEDIDO", sequenceName = "SQ_T_PEDIDO", allocationSize = 1)
public class Request {

    @Id
    @Column(name = "CD_PEDIDO", length = 3, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_T_PEDIDO")
    private Long id;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "CD_HOSPITAL", foreignKey = @ForeignKey(name = "FK_SL_PEDIDO_HOSPITAL"))
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "CD_USUARIO", foreignKey = @ForeignKey(name = "FK_SL_PEDIDO_USUARIO"), nullable = false)
    private UserModel user;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "CD_AMBULANCIA", foreignKey = @ForeignKey(name = "FK_SL_PEDIDO_AMBULANCIA"), nullable = false)
    private Ambulance ambulance;

    @Column(name = "DT_DATA_DO_PEDIDO", nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "ST_NIVEL_DE_URGENCIA", length = 15, nullable = false)
    private Urgency urgency;

    @Lob
    @Column(name = "DS_CASO", nullable = false)
    private String description;

    public Request() {
    }

    public Request(Long id, Hospital hospital, UserModel user, Ambulance ambulance, LocalDate date, Urgency urgency, String description) {
        this.id = id;
        this.hospital = hospital;
        this.user = user;
        this.ambulance = ambulance;
        this.date = date;
        this.urgency = urgency;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Ambulance getAmbulance() {
        return ambulance;
    }

    public void setAmbulance(Ambulance ambulance) {
        this.ambulance = ambulance;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
