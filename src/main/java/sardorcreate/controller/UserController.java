package sardorcreate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sardorcreate.dto.user.UserCreateDto;
import sardorcreate.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserCreateDto dto) {

        return userService.createUser(dto);
    }

    @GetMapping("/get/dep_id/{id}")
    public ResponseEntity<?> getUserByDep(@PathVariable long id) {

        return userService.getUserByDep(id);
    }
}