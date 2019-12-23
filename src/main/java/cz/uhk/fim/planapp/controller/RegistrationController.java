package cz.uhk.fim.planapp.controller;

import com.mysql.cj.Session;
import cz.uhk.fim.planapp.common.GoogleMail;
import cz.uhk.fim.planapp.domain.User;
import cz.uhk.fim.planapp.dto.UserDto;
import cz.uhk.fim.planapp.repository.UserRepository;

import cz.uhk.fim.planapp.service.MapValidationErrorService;
import cz.uhk.fim.planapp.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;

@RestController
public class RegistrationController {

    @Autowired
    private RegisterService registerService;

    /*@Autowired
    private MapValidationErrorService mapValidationErrorService;*/

    /*@Autowired
    private UserRepository userRepository;*/

    /*@Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;*/

    @RequestMapping(value = "/confirm-account/{visibleId}", method = RequestMethod.GET)
    public void confirmUser(@PathVariable Integer visibleId){
        registerService.confirmUserByVisibleId(visibleId.toString());
        System.out.println("User has been confirmed :)");
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> loginUser(@RequestBody User user, BindingResult result, HttpSession session) {

        String[] recipient = { user.getEmail() };

        /*ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null){
            return errorMap;
        }*/

        registerService.saveOrUpdateUser(user);

        GoogleMail googleMail = new GoogleMail();
        googleMail.sendFromGMail(
                "lausman.marek",
                "nzmlvheusumnjivq", recipient,
                "Registration confirmation - PLANAPP",
                "Clink the link below to activate your account on PlanApp! :)" + "\n http://localhost:8081/confirm-account/" + user.getVisibleId()
        );
        /*ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("chand312902@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8081/confirm-account?token="+confirmationToken.getConfirmationToken());

        emailSenderService.sendEmail(mailMessage);*/

        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
}