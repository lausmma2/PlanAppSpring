package cz.uhk.fim.planapp.controller;

import cz.uhk.fim.planapp.domain.User;
import cz.uhk.fim.planapp.dto.UserDto;
        import cz.uhk.fim.planapp.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.ws.rs.Path;
import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;

@RestController //vrací JSON v metodách
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    //private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("api/user/{visibleId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable String visibleId, Principal principal){
        User user = userService.findUserByVisibleId(visibleId, principal.getName());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("api/user/user-info")
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

    /*@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestParam MultipartFile file) {
        logger.info(String.format("File name '%s' was uploaded successfully.", file.getOriginalFilename()));
        return ResponseEntity.ok().build();
    }*/
}