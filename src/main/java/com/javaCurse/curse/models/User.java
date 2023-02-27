package com.javaCurse.curse.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Long id;
    @Getter @Setter @Column(name = "name")
    private String name;
    @Getter @Setter @Column(name = "last_name")
    private String lastName;
    @Getter @Setter @Column(name = "email")
    private String email;
    @Getter @Setter @Column(name = "phone_number")
    private String phoneNumber;
    @Getter @Setter @Column(name = "password")
    private String password;



}
