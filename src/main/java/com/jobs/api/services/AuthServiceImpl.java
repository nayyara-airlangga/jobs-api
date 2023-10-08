package com.jobs.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobs.api.dtos.responses.LoginRes;
import com.jobs.api.models.User;
import com.jobs.api.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Override
    public LoginRes login(String username, String password) {
        String accessToken;

        var user = userRepository.findByUsername(username);
        // Present = Login
        // Not Present = Register
        if (user.isPresent()) {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var userEnt = user.get();

            accessToken = jwtService.generateToken(userEnt.getUsername(), null);
        } else {
            var newUser = User.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .active(true)
                    .build();

            newUser = userRepository.save(newUser);

            accessToken = jwtService.generateToken(newUser.getUsername(), null);
        }

        return LoginRes.builder().accessToken(accessToken).build();
    }
}
