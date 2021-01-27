package mk.ukim.finki.theatermanagement.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SceneNotFoundException extends RuntimeException{
    public SceneNotFoundException() {
    }
}
