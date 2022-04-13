package com.example.usermanagment.mapper;

import com.example.usermanagment.entities.Phone;
import com.example.usermanagment.entities.User;
import com.example.usermanagment.service.dto.UserInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class UserInDTOToUser implements IMapper<UserInDTO, User> {
    @Override
    public User map(final UserInDTO in) {
        User user = new User();
        user.setCityCode(in.getCityCode());
        user.setCountryCode(in.getCountryCode());
        user.setEmail(in.getEmail());
        user.setName(in.getName());
        user.setPassword(in.getPassword());
        user.setCreationTime(LocalDate.now());
        user.setUpdateTime(LocalDate.now());
        user.setLastLogin(LocalDate.now());
        user.setToken(UUID.randomUUID().toString().replace("-", ""));
        in.getPhones().forEach(phone -> {
            Phone phone1 = new Phone();
            phone1.setNumber(phone.getNumber());
            phone1.setUser(user);
            user.getPhones().add(phone1);
        });
        return user;
    }
}
