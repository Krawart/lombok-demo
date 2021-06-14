package com.krawart.lombok.user.presentation.v1.models;

import com.krawart.lombok.user.domain.User;
import lombok.Data;

@Data
public class UserUpdateDTO {
    private String firstName;
    private String lastName;
    private int age;

    public User mapToDomain(long id) {
        return User.builder().id(id).firstName(firstName).lastName(lastName).age(age).build();
    }
}
