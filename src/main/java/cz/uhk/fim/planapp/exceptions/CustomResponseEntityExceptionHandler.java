package cz.uhk.fim.planapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleUsernameExistsException(UsernameExistsException ex, WebRequest request){
        UsernameExistsExceptionResponse userNameExceptionResponse = new UsernameExistsExceptionResponse(ex.getMessage());
        return new ResponseEntity(userNameExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleTripIdException(TripIdException ex, WebRequest request){
        TripIdExceptionResponse exceptionResponse = new TripIdExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleTripNotFoundException(TripNotFoundException ex, WebRequest request){
        TripNotFoundExceptionResponse exceptionResponse = new TripNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request){
        UserNotFoundExceptionResponse exceptionResponse = new UserNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleUserNotConfirmedException(UserNotConfirmedException ex, WebRequest request){
        UserNotConfirmedExceptionResponse exceptionResponse = new UserNotConfirmedExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleFirstnameIsEmptyException(FirstnameIsEmptyException ex, WebRequest request){
        FirstnameIsEmptyExceptionResponse exceptionResponse = new FirstnameIsEmptyExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleLastnameIsEmptyException(LastnameIsEmptyException ex, WebRequest request){
        LastnameIsEmptyExceptionResponse exceptionResponse = new LastnameIsEmptyExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleTripGroupIdException(TripGroupIdException ex, WebRequest request){
        TripGroupIdExceptionResponse exceptionResponse = new TripGroupIdExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleTripGroupNotFoundException(TripGroupNotFoundException ex, WebRequest request){
        TripGroupNotFoundExceptionResponse exceptionResponse = new TripGroupNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}