package com.krawart.lombok.user.application;

import com.krawart.lombok.user.domain.User;
import com.krawart.lombok.user.infrastructure.UserEntity;
import com.krawart.lombok.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User saveUser(User user) {
        UserEntity persistedUser = userRepository.save(UserEntity.of(user));
        return persistedUser.mapToDomain();
    }

    public User updateUser(User user) {
        return saveUser(user);
    }

    public Optional<User> findById(Long id) {
        Optional<UserEntity> foundUser = userRepository.findById(id);
        return foundUser.map(UserEntity::mapToDomain);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
