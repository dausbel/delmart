package com.example.usermanagment.controler;

import com.example.usermanagment.service.UserService;
import com.example.usermanagment.service.dto.UserInDTO;
import com.example.usermanagment.service.dto.UserOutDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(UserController.PATH)
@Validated
@Api(tags = "Users")
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {
    static final String PATH = "api/v1/users";
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserOutDTO> createUser(@Valid @RequestBody UserInDTO userInDTO) {
        log.debug("saving user: " + userInDTO);
        return new ResponseEntity<>(this.userService.createUser(userInDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<UserOutDTO> getUser(@RequestParam("userId") Long userId) {
        log.debug("getting user: " + userId);
        return new ResponseEntity<>(this.userService.getUser(userId), HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<UserOutDTO>> getAllUser() {
        log.debug("getting all user: ");
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        this.userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
