package ru.alsi.jwtapp.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alsi.jwtapp.dto.UserDto;
import ru.alsi.jwtapp.model.User;
import ru.alsi.jwtapp.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;


    @GetMapping(value = "users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        User result = userService.findById(id);
        if(result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto dto = UserDto.fromUser(result);
        return ResponseEntity.ok(dto);
    }

}
