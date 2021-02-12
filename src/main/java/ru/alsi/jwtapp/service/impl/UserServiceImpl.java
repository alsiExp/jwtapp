package ru.alsi.jwtapp.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.alsi.jwtapp.model.Status;
import ru.alsi.jwtapp.model.User;
import ru.alsi.jwtapp.repository.RoleRepository;
import ru.alsi.jwtapp.repository.UserRepository;
import ru.alsi.jwtapp.service.UserService;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User register(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(roleRepository.findByName("ROLE_USER")));

        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);
        log.info("IN register  - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> all = userRepository.findAll();
        log.info("IN getAll - {} users found", all.size());
        return all;
    }

    @Override
    public User findByUsername(String username) {
        User byUsername = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", byUsername, username);
        return byUsername;
    }

    @Override
    public User findById(Long id) {
        User byId = userRepository.findById(id).orElse(null);
        if(byId == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }
        log.info("IN findById - user: {} found by id: {}", byId, id);
        return byId;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
    }
}
