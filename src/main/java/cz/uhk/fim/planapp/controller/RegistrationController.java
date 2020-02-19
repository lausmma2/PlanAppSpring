package cz.uhk.fim.planapp.controller;

import cz.uhk.fim.planapp.common.GoogleMail;
import cz.uhk.fim.planapp.domain.User;

import cz.uhk.fim.planapp.service.MapValidationErrorService;
import cz.uhk.fim.planapp.service.RegisterService;
import cz.uhk.fim.planapp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RegistrationController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @RequestMapping(value = "/confirm-account/{visibleId}", method = RequestMethod.GET)
    public void confirmUser(@PathVariable Integer visibleId){
        registerService.confirmUserByVisibleId(visibleId.toString());
        System.out.println("User has been confirmed :)");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> loginUser(@Valid @RequestBody User user, BindingResult result) {

        userValidator.validate(user, result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null){
            return errorMap;
        }

        User newUser = registerService.saveOrUpdateUser(user);

        //Confirmation e-mail sending
        String[] recipient = { user.getUsername() };
        GoogleMail googleMail = new GoogleMail();
        googleMail.sendFromGMail(
                "lausman.marek",
                "nzmlvheusumnjivq", recipient,
                "Registration confirmation - PLANAPP",
                "Click the link below to activate your account on PlanApp! :)" + "\n http://localhost:8081/confirm-account/" + user.getVisibleId()
        );

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
}