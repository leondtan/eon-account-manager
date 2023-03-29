package dev.eon.accountmanager.service;

import dev.eon.accountmanager.entity.User;
import dev.eon.accountmanager.exception.GeneralException;
import dev.eon.accountmanager.model.request.CreateUserRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User register(String email, String name, String password, String role);

    User register(CreateUserRequest createUserRequest);

    User getByEmail(String email);
}
