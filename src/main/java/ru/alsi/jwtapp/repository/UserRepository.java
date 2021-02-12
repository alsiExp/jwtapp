package ru.alsi.jwtapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alsi.jwtapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
