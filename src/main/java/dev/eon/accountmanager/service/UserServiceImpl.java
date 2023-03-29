package dev.eon.accountmanager.service;

import dev.eon.accountmanager.entity.User;
import dev.eon.accountmanager.exception.GeneralException;
import dev.eon.accountmanager.feedback.ErrorCode;
import dev.eon.accountmanager.model.request.CreateUserRequest;
import dev.eon.accountmanager.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository _userRepo;

    private final PasswordEncoder _passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this._userRepo = userRepository;
        this._passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String email, String name, String password, String role) {
        User newUser = new User(
                email,
                name,
                _passwordEncoder.encode(password),
                role
        );

        newUser = _userRepo.save(newUser);

        return newUser;
    }

    @Override
    @SneakyThrows
    public User register(CreateUserRequest createUserRequest) {
        if(_userRepo.findByEmail(createUserRequest.getEmail()).isPresent()) {
            throw new GeneralException(ErrorCode.USER_EXISTED);
        }
        User newUser = new User(
                createUserRequest.getEmail(),
                createUserRequest.getName(),
                _passwordEncoder.encode(createUserRequest.getPassword()),
                createUserRequest.getRole()
        );

        newUser = _userRepo.save(newUser);

        return newUser;
    }

    @Override
    public User getByEmail(String email) {
        return _userRepo.findByEmail(email).orElse(null);
    }
}
