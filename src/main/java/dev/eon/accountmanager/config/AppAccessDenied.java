package dev.eon.accountmanager.config;

import dev.eon.accountmanager.feedback.ErrorCode;
import dev.eon.accountmanager.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class AppAccessDenied implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        Principal user = request.getUserPrincipal();
        ErrorResponse error = new ErrorResponse(
                ErrorCode.AUTH_USER_NOT_ALLOWED.getDescription(),
                HttpStatus.FORBIDDEN,
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
        );

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.getWriter().write(error.toJson());
    }

}