package com.gwpublic.demo.service.impl;

import com.gwpublic.demo.dto.Customer;
import com.gwpublic.demo.dto.JwtAuthenticationResponse;
import com.gwpublic.demo.dto.SignUpRequest;
import com.gwpublic.demo.dto.SigninRequest;
import com.gwpublic.demo.repository.CustomerRepository;
import com.gwpublic.demo.service.AuthenticationService;

import com.gwpublic.demo.service.JwtAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtAppService jwtAppService;
    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = Customer.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .birthDate(request.getBirthDate())
                .gsmNumber(request.getGsmNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        customerRepository.createCustomer(user);
        var jwt = jwtAppService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getGsmNumber(), request.getPassword()));
        var user = customerRepository.getCustomer(request.getGsmNumber());
        var jwt = jwtAppService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

}
