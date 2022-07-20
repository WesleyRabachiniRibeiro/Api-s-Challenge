package com.savelife.user.entities;

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
@Table(name = "T_SL_TELEFONE")
@SequenceGenerator(name = "SQ_T_PHONE", sequenceName = "SQ_T_PHONE", allocationSize = 1)
public class Phone {

    @Id
    @Column(name = "CD_TELEFONE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_T_PHONE")
    private Long id;

    @ManyToOne
    @JoinColumn(name="cd_user", nullable = false)
    private User user;

    private String phone;

    public Phone(){}

    public Phone(Long id, String phone) {
        this.id = id;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
