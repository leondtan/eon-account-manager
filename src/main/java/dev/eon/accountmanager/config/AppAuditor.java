package dev.eon.accountmanager.config;

import dev.eon.accountmanager.entity.User;
import dev.eon.accountmanager.service.UserService;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppAuditor implements AuditorAware<User> {

    private UserService _userService;

    public AppAuditor(UserService userService) {
        this._userService = userService;
    }

    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> currentUser = Optional.empty();

        if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
            String userEmail = auth.getName();
            if (userEmail != null) {
                currentUser = Optional.of(_userService.getByEmail(userEmail));
            }
        }

        return currentUser;
    }
}