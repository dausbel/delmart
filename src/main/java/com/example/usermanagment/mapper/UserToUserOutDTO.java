package com.example.usermanagment.mapper;

import com.example.usermanagment.entities.User;
import com.example.usermanagment.service.dto.PhoneInDTO;
import com.example.usermanagment.service.dto.UserOutDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class UserToUserOutDTO implements IMapper<User, UserOutDTO> {
    @Override
    public UserOutDTO map(final User user) {
        UserOutDTO userOutDTO = new UserOutDTO();
        userOutDTO.setCityCode(user.getCityCode());
        userOutDTO.setCountryCode(user.getCountryCode());
        userOutDTO.setEmail(user.getEmail());
        userOutDTO.setName(user.getName());
        user.getPhones().forEach(phone -> {
            PhoneInDTO phone1 = new PhoneInDTO();
            phone1.setNumber(phone.getNumber());
            userOutDTO.getPhones().add(phone1);
        });
        boolean isActive = getActiveness(user);
        userOutDTO.setActive(isActive);
        userOutDTO.setCreated(user.getCreationTime());
        userOutDTO.setModified(user.getUpdateTime());
        userOutDTO.setLastLogin(user.getLastLogin());
        userOutDTO.setToken(user.getToken());
        userOutDTO.setId(user.getId());
        return userOutDTO;
    }

    private boolean getActiveness(User user) {
        if (Period.between(LocalDate.now(), user.getLastLogin()).getDays() > 5) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }
}
