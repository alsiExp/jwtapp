package ru.alsi.jwtapp.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alsi.jwtapp.dto.UserDto;
import ru.alsi.jwtapp.model.User;
import ru.alsi.jwtapp.security.jwt.JwtTokenProvider;
import ru.alsi.jwtapp.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;


    @GetMapping(value = "self")
    public ResponseEntity<UserDto> getSelf(HttpServletRequest request) {
        String name = jwtTokenProvider.getUserName(jwtTokenProvider.resolveToken(request));
        User result = userService.findByUsername(name);
        if(result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto dto = UserDto.fromUser(result);
        return ResponseEntity.ok(dto);
    }
}
