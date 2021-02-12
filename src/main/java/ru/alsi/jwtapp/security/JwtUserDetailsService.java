package ru.alsi.jwtapp.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.alsi.jwtapp.model.User;
import ru.alsi.jwtapp.security.jwt.JwtUser;
import ru.alsi.jwtapp.security.jwt.JwtUserFactory;
import ru.alsi.jwtapp.service.UserService;

@Service
@Slf4j
@AllArgsConstructor
public class JwtUserDetailsService  implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("User with username = " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully load", username);
        return jwtUser;
    }
}
