package com.savelife.project.entities;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_SL_FUNCAO",
        uniqueConstraints = {@UniqueConstraint(name = "UN_SL_FUNCAO",
                columnNames = {"NM_FUNCAO"})})
@SequenceGenerator(name = "SQ_T_FUNCAO", sequenceName = "SQ_T_FUNCAO", allocationSize = 1)
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "CD_FUNCAO", length = 3, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_T_FUNCAO")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "NM_FUNCAO", length = 30, unique = true)
    private Roles name;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.DETACH)
    private List<UserModel> users;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role(Roles roles) {
        this.name = roles;
    }

    public Roles getName() {
        return name;
    }

    public void setName(Roles name) {
        this.name = name;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return this.name.toString();
    }

}