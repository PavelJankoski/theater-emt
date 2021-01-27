package mk.ukim.finki.usermanagement.port.dto.response;

import java.util.List;

public class JwtResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer";
    private String name;
    private String surname;
    private String email;
    private List<String> roles;

    public JwtResponseDTO(String accessToken,String name, String surname, String email, List<String> roles) {
        this.accessToken = accessToken;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roles = roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }


    public String getName() {
        return name;
    }


    public String getSurname() {
        return surname;
    }


    public String getEmail() {
        return email;
    }


    public List<String> getRoles() {
        return roles;
    }
}
