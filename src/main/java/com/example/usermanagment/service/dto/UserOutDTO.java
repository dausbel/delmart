package com.example.usermanagment.service.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserOutDTO {

    private Long id;
    private String name;
    private String email;
    private String cityCode;
    private String countryCode;
    private Set<PhoneInDTO> phones = new HashSet<>();
    private LocalDate modified;
    private LocalDate created;
    private LocalDate lastLogin;
    private String token;
    private boolean isActive;



}
