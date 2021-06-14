package com.krawart.lombok.user.presentation.v1.models;

import com.krawart.lombok.user.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;

    public static UserDTO of(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .build();
    }

    public User mapToDomain() {
        return User.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .build();
    }
}
