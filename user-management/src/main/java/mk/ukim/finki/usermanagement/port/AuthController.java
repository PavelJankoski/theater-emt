package mk.ukim.finki.usermanagement.port;

import mk.ukim.finki.usermanagement.application.service.UserService;
import mk.ukim.finki.usermanagement.port.dto.request.LoginRequestDTO;
import mk.ukim.finki.usermanagement.port.dto.request.RegisterRequestDTO;
import mk.ukim.finki.usermanagement.port.dto.response.JwtResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public HttpStatus registerUser(@Valid @RequestBody RegisterRequestDTO registerRequestDTO){
        this.userService.signUpUser(registerRequestDTO);
        return HttpStatus.OK;
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponseDTO> loginUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok().body(this.userService.signInUser(loginRequestDTO));
    }
}
