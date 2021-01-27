package mk.ukim.finki.usermanagement.application.service;

import mk.ukim.finki.usermanagement.port.dto.request.LoginRequestDTO;
import mk.ukim.finki.usermanagement.port.dto.request.RegisterRequestDTO;
import mk.ukim.finki.usermanagement.port.dto.response.JwtResponseDTO;

public interface UserService {
    JwtResponseDTO signInUser(LoginRequestDTO loginRequestDTO);

    void signUpUser(RegisterRequestDTO registerRequestDTO);
}
