package cz.uhk.fim.planapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TripGroupNotFoundException extends RuntimeException{

    public TripGroupNotFoundException(String message){
        super(message);
    }
}

