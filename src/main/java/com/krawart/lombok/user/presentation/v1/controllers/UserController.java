package com.krawart.lombok.user.presentation.v1.controllers;

import com.krawart.lombok.user.application.UserService;
import com.krawart.lombok.user.domain.User;
import com.krawart.lombok.user.application.exceptions.UserNotFoundException;
import com.krawart.lombok.user.presentation.v1.models.UserDTO;
import com.krawart.lombok.user.presentation.v1.models.UserMutationDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Value("${server.address}")
    private String address;

    @Value("${server.port}")
    private String port;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        UserDTO responseBody = modelMapper.map(user, UserDTO.class);
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserMutationDTO user) {
        User userToStore = modelMapper.map(user, User.class);

        User persistedUser = userService.saveUser(userToStore);

        return ResponseEntity.created(URI.create(getControllerBaseUrl() + persistedUser.getId())).build();
    }

    private String getControllerBaseUrl() {
        return "https://" + address + ":" + port + "/api/users/";
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserMutationDTO user) {
        User userToStore = modelMapper.map(user, User.class);
        userToStore.setId(id);

        User updatedUser = userService.updateUser(userToStore);

        UserDTO responseBody = modelMapper.map(updatedUser, UserDTO.class);
        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
