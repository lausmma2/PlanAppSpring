package cz.uhk.fim.planapp.controller;

import cz.uhk.fim.planapp.common.GoogleMail;
import cz.uhk.fim.planapp.domain.User;

import cz.uhk.fim.planapp.security.JwtTokenProvider;
import cz.uhk.fim.planapp.service.MapValidationErrorService;
import cz.uhk.fim.planapp.service.RegisterService;
import cz.uhk.fim.planapp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.UUID;

import static cz.uhk.fim.planapp.security.SecurityConstants.TOKEN_PREFIX;

@RestController
public class RegistrationController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @RequestMapping(value = "/confirm-account/{username}/{uuid}", method = RequestMethod.GET)
    public void confirmUser(@PathVariable String username,
                            @PathVariable String uuid){
        //registerService.confirmUserByVisibleId(visibleId.toString());
        registerService.confirmUserByUsername(username);
        System.out.println("User has been confirmed :)");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> loginUser(@Valid @RequestBody User user, BindingResult result) {

        userValidator.validate(user, result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null){
            return errorMap;
        }

        String uniqueID = UUID.randomUUID().toString();

        //Confirmation e-mail sending
        String[] recipient = { user.getUsername() };
        GoogleMail googleMail = new GoogleMail();
        googleMail.sendFromGMail(
                "lausman.marek",
                "nzmlvheusumnjivq", recipient,
                "Registration confirmation - PlanApp",
                "Click the link below to activate your account on PlanApp! :)" + "\n https://planapp-spring.herokuapp.com/confirm-account/" + user.getUsername() + "/" + uniqueID/*user.getVisibleId()*/
        );

        User newUser = registerService.saveOrUpdateUser(user);

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
}