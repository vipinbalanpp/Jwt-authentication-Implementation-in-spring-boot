package com.vipin.jwt_authentication_implementation.service.impl;
import com.vipin.jwt_authentication_implementation.repository.UserRepository;
import com.vipin.jwt_authentication_implementation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
}
