package com.fpoly.sof3012.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name= "Id")
    private String id;

    @Column(name= "Password")
    private String password;

    @Column(name= "Fullname")
    private String fullname;

    @Column(name= "Email")
    private String email;

    @Column(name= "Admin")
    private Boolean admin;

    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "user") // ket noi voi field user ben Share
    private List<Favorite> shares;
}
