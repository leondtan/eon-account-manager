package dev.eon.accountmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcDaoImpl service = new JdbcDaoImpl();
        service.setDataSource(dataSource);
        service.setUsersByUsernameQuery("select email, password, true from users where email = ? ");
        service.setAuthoritiesByUsernameQuery("select email, role from users where email = ?");

        return service;
    }

    @Autowired
    private AppNoAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private AppAccessDenied accessDenied;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .accessDeniedHandler(accessDenied)

                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/test/**")
                .permitAll()
                .antMatchers("/api/v1/register")
                .anonymous()
                .antMatchers(HttpMethod.POST ,"/api/v1/balance")
                .hasAuthority("admin")
                .antMatchers(HttpMethod.GET ,"/api/v1/balance/summary/user")
                .hasAuthority("admin")
                .anyRequest()
                .authenticated()

                .and()
                .httpBasic();

        return http.build();
    }

}
