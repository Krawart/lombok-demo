package com.krawart.lombok.user.presentation.v1.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserMutationDTO {
    String firstName;
    String lastName;
    int age;
}
