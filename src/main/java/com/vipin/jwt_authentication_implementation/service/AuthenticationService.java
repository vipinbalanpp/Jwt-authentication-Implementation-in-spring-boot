package com.vipin.jwt_authentication_implementation.service;
import com.vipin.jwt_authentication_implementation.model.dto.AuthenticationResponse;
import com.vipin.jwt_authentication_implementation.model.dto.RegisterRequest;
import com.vipin.jwt_authentication_implementation.model.entity.Role;
import com.vipin.jwt_authentication_implementation.model.entity.User;
import com.vipin.jwt_authentication_implementation.repository.UserRepository;
import com.vipin.jwt_authentication_implementation.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User
                .builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        System.out.println(user+"--> saved user");
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(RegisterRequest request) {
        System.out.println(request);
        System.out.println("one");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(SecurityContextHolder.getContext().getAuthentication()+"---<authentication>----");
        System.out.println("two");
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow()  ;
        var jwtToken = jwtService.generateToken(user);
        System.out.println("three");
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();

    }

}
