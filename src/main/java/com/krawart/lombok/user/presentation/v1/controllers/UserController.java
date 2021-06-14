package com.krawart.lombok.user.presentation.v1.controllers;

import com.krawart.lombok.user.application.UserService;
import com.krawart.lombok.user.domain.User;
import com.krawart.lombok.user.application.exceptions.UserNotFoundException;
import com.krawart.lombok.user.presentation.v1.models.UserDTO;
import com.krawart.lombok.user.presentation.v1.models.UserUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return ResponseEntity.ok(UserDTO.of(user));
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDTO user) {
        User persistedUser = userService.saveUser(user.mapToDomain());
        return ResponseEntity.created(URI.create("https://localhost:9000/api/users/" + persistedUser.getId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateDTO user) {
        User updatedUser = userService.updateUser(user.mapToDomain(id));
        return ResponseEntity.ok(UserDTO.of(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
