package cz.uhk.fim.planapp.controller;

import cz.uhk.fim.planapp.domain.User;
import cz.uhk.fim.planapp.exceptions.UserNotConfirmedException;
import cz.uhk.fim.planapp.exceptions.UserNotFoundException;
import cz.uhk.fim.planapp.payload.JWTLoginSuccessResponse;
import cz.uhk.fim.planapp.payload.LoginRequest;
import cz.uhk.fim.planapp.repository.UserRepository;
import cz.uhk.fim.planapp.security.JwtTokenProvider;
import cz.uhk.fim.planapp.service.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static cz.uhk.fim.planapp.security.SecurityConstants.TOKEN_PREFIX;

@RestController
public class LoginController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserRepository userRepository;

    //Class takes care of authentication and log user in
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null){
            return errorMap;
        }
        try {
            String jwt = "";
            User user = userRepository.findByUsername(loginRequest.getUsername());

            //User has to be confirmed in email, otherwise user won't be able to log in
            if (user.getConfirmed()) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword()
                        )
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
                jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
            } else {
                throw new UserNotConfirmedException("User has not been confirmed yet!");
            }
            return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
        }catch (Exception ex){
            throw new UserNotFoundException("Username or password is invalid! Or you haven't confirmed your account yet!");
        }
    }
}
