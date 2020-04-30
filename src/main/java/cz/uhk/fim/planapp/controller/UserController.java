package cz.uhk.fim.planapp.controller;

import cz.uhk.fim.planapp.domain.User;
import cz.uhk.fim.planapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

//Class maintaining all operations with user
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("api/user/{visibleId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable String visibleId, Principal principal){
        User user = userService.findUserByVisibleId(visibleId, principal.getName());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/user-info")
    public ResponseEntity<?> getCurrentsUserInfo(Principal principal){
        User user = userService.findUserByUsername(principal.getName());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("api/user/user-info/update")
    public ResponseEntity<?> updateUsersInfo(@RequestBody User updatedUser, Principal principal){
        User userInDb = userService.findUserByUsername(principal.getName());
        userService.updateUser(userInDb, updatedUser);
        return new ResponseEntity<User>(userInDb, HttpStatus.OK);
    }
}