package mk.ukim.finki.usermanagement.application.service.impl;

import mk.ukim.finki.sharedkernel.domain.name.FullName;
import mk.ukim.finki.usermanagement.application.service.RoleService;
import mk.ukim.finki.usermanagement.application.service.UserService;
import mk.ukim.finki.usermanagement.domain.exceptions.UserAlreadyExistsException;
import mk.ukim.finki.usermanagement.domain.model.Role;
import mk.ukim.finki.usermanagement.domain.model.User;
import mk.ukim.finki.usermanagement.domain.repository.UserRepository;
import mk.ukim.finki.usermanagement.port.dto.request.LoginRequestDTO;
import mk.ukim.finki.usermanagement.port.dto.request.RegisterRequestDTO;
import mk.ukim.finki.usermanagement.port.dto.response.JwtResponseDTO;
import mk.ukim.finki.usermanagement.security.jwt.JwtUtils;
import mk.ukim.finki.usermanagement.security.services.UserDetailsImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }


    @Override
    public JwtResponseDTO signInUser(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new JwtResponseDTO(jwt, userDetails.getName(), userDetails.getSurname(), userDetails.getUsername(), roles);
    }

    @Transactional
    @Override
    public void signUpUser(RegisterRequestDTO registerRequestDTO) {
        if (userRepository.findByEmail(registerRequestDTO.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(registerRequestDTO.getEmail());
        }
        FullName fullName = new FullName(registerRequestDTO.getName(), registerRequestDTO.getSurname());
        Role r = roleService.getRolesByName(registerRequestDTO.getRoles());
        User user = User.createUser(fullName, registerRequestDTO.getEmail(), passwordEncoder.encode(registerRequestDTO.getPassword()), r);
        userRepository.save(user);
    }
}
