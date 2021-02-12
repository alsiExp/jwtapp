package ru.alsi.jwtapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alsi.jwtapp.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
