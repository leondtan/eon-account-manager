package dev.eon.accountmanager.controller;

import dev.eon.accountmanager.entity.User;
import dev.eon.accountmanager.model.request.CreateUserRequest;
import dev.eon.accountmanager.model.response.SuccessResponse;
import dev.eon.accountmanager.service.UserService;
import dev.eon.accountmanager.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${app.base-path}")
public class UserController {

    private UserService _service;

    public UserController(
            UserService userService
    ) {
        this._service = userService;
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody CreateUserRequest createUserModel) {
        User newUser = _service.register(createUserModel);

        return ResponseEntity.ok(new SuccessResponse(
                newUser,
                HttpStatus.CREATED
        ));
    }

    @GetMapping("me")
    public ResponseEntity<?> info() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User currentUser = _service.getByEmail(username);

        return ResponseEntity.ok(new SuccessResponse(
                currentUser,
                HttpStatus.ACCEPTED
        ));
    }
}
