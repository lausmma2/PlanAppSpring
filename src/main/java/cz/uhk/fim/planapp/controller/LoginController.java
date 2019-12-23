package cz.uhk.fim.planapp.controller;

import cz.uhk.fim.planapp.domain.User;
import cz.uhk.fim.planapp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.Base64;
import java.util.Objects;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<?> loginUser(@PathParam("email") String email, @PathParam("password") String password, HttpSession session, RedirectAttributes attributes) {
        User user = loginService.validUser(email, password);

        if (Objects.isNull(user)) {
            attributes.addFlashAttribute("error", "error");
            System.out.println("User not found...");

            return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
        } else {
            session.setAttribute("user", user);
            System.out.println("Logged in :) ");
            /*if (!Objects.isNull(user.getPhone())){
                byte[] encoded = Base64.getEncoder().encode(user.getPhoto());
                session.setAttribute("image", new String(encoded));
            }*/
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        }

    }
}
