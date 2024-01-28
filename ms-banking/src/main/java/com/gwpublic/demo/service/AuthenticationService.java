package com.gwpublic.demo.service;


import com.gwpublic.demo.dto.JwtAuthenticationResponse;
import com.gwpublic.demo.dto.SignUpRequest;
import com.gwpublic.demo.dto.SigninRequest;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

}
