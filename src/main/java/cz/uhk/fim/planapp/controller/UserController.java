package cz.uhk.fim.planapp.controller;

import cz.uhk.fim.planapp.domain.User;
import cz.uhk.fim.planapp.dto.UserDto;
        import cz.uhk.fim.planapp.service.UserService;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //vrací JSON v metodách
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<UserDto> showUsers() {
        List<UserDto> users = userService.getAllUsers();
        return users;
    }

    @RequestMapping(value = "/admin/user-page", method = RequestMethod.GET)
    public UserDto showUser(){
        UserDto user = userService.getUserById(1);
        return user;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable Integer userId, BindingResult result){
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }
}