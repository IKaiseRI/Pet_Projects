package com.example.devApplication.service;

import com.example.devApplication.entity.Role;
import com.example.devApplication.entity.User;
import com.example.devApplication.repository.RoleRepository;
import com.example.devApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()))
                .setRoles(Set.of(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }

    public String getRole(String username) {
        List<String> permissions = userRepository
                .findByUsername(username)
                    .getRoles()
                        .stream()
                        .map(Role::getName)
                        .toList();
        if(permissions.contains("ROLE_ADMIN"))
            return "ADMIN";
        else return "USER";
    }
}
