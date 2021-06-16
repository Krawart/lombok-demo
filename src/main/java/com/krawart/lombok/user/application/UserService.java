package com.krawart.lombok.user.application;

import com.krawart.lombok.user.domain.User;
import com.krawart.lombok.user.infrastructure.UserEntity;
import com.krawart.lombok.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public User saveUser(User user) {
        UserEntity userToStore = modelMapper.map(user, UserEntity.class);
        UserEntity persistedUser = userRepository.save(userToStore);
        return modelMapper.map(persistedUser, User.class);
    }

    public User updateUser(User user) {
        return saveUser(user);
    }

    public Optional<User> findById(Long id) {
        Optional<UserEntity> foundUser = userRepository.findById(id);
        return foundUser.map(user -> modelMapper.map(user, User.class));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
