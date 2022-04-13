package com.example.usermanagment.service;

import com.example.usermanagment.entities.User;
import com.example.usermanagment.exceptions.UserExceptions;
import com.example.usermanagment.mapper.UserInDTOToUser;
import com.example.usermanagment.mapper.UserToUserOutDTO;
import com.example.usermanagment.persistence.repository.UserRepository;
import com.example.usermanagment.service.dto.UserInDTO;
import com.example.usermanagment.service.dto.UserOutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserService {

    private final UserRepository userRepository;
    private final UserInDTOToUser mapper;
    private final UserToUserOutDTO mapperOut;

    @Transactional
    public UserOutDTO createUser(UserInDTO userInDTO) {
        return Optional.ofNullable(userInDTO)
            .filter(userInDTO1 -> !this.userRepository.existsByEmail(userInDTO1.getEmail()))
            .map(userInDTO1 -> Optional.of(userInDTO1)
                .map(mapper::map)
                .map(userRepository::save)
                .map(mapperOut::map)
                .orElseThrow(() -> new UserExceptions("Could no be saved", HttpStatus.BAD_REQUEST)))
            .orElseThrow(() -> new UserExceptions("Email already exists", HttpStatus.BAD_REQUEST));
    }

    public UserOutDTO getUser(Long userId) {
        return this.userRepository.findById(userId)
            .map(mapperOut::map)
            .orElseThrow(() -> new UserExceptions("User Not Found", HttpStatus.NOT_FOUND));

    }

    public List<UserOutDTO> getAllUsers() {
        return this.userRepository.findAll().stream().map(mapperOut::map).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        final Optional<User> byId = this.userRepository.findById(id);
        if (byId.isPresent()) {
            throw new UserExceptions("User Not Found", HttpStatus.NOT_FOUND);
        }
        this.userRepository.deleteById(byId.get().getId());
    }

}
