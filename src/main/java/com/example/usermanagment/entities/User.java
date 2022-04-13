package com.example.usermanagment.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "city_code", nullable = false)
    private String cityCode;

    @Column(name = "country_code", nullable = false)
    private String countryCode;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference
    private Set<Phone> phones = new HashSet<>();

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "update_time")
    @UpdateTimestamp
    private LocalDate updateTime;

    @Column(name = "creation_time", nullable = false)
    @CreationTimestamp
    private LocalDate creationTime;

    @Column(name = "last_login")
    @UpdateTimestamp
    private LocalDate lastLogin;



}
