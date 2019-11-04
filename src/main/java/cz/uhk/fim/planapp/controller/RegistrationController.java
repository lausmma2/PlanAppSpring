package cz.uhk.fim.planapp.controller;

import cz.uhk.fim.planapp.domain.User;
import cz.uhk.fim.planapp.dto.UserDto;
import cz.uhk.fim.planapp.service.MapValidationErrorService;
import cz.uhk.fim.planapp.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@RestController
public class RegistrationController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> loginUser(@Valid @RequestBody User user, BindingResult result, HttpSession session) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null){
            return errorMap;
        }

        registerService.saveOrUpdateUser(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
}