package cz.uhk.fim.planapp.service;

import cz.uhk.fim.planapp.domain.User;
import cz.uhk.fim.planapp.exceptions.UsernameExistsException;
import cz.uhk.fim.planapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveOrUpdateUser(User newUser) {

        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setUsername(newUser.getUsername());

            //Nobody needs to see confirm password in db
            newUser.setConfirmPassword("");

            setCreated_OnDate(newUser);

            return userRepository.save(newUser);
        }catch (Exception ex){
            throw new UsernameExistsException("Username: " + newUser.getUsername() + " already exists!");
        }
    }

    @Transactional
    public void setCreated_OnDate(User user){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDateTime now = LocalDateTime.now();
        dtf.format(now);
        user.setCreated_On(now);
    }

    @Transactional
    public void generateUsersVisibleId(User user){
        Long count = (userRepository.count() + 1) + 100 + 913;
        user.setVisibleId(count.toString());
    }

    @Transactional
    public void confirmUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        user.setConfirmed(true);
    }

    @Transactional
    public void confirmUserByVisibleId(String visibleId){
        User user = userRepository.findUserByVisibleId(visibleId.toString());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDateTime tomorrow = user.getCreated_On().plusHours(24);
        dtf.format(tomorrow);
        LocalDateTime now = LocalDateTime.now();

        if(now.isBefore(tomorrow)){
            user.setConfirmed(true);
            System.out.println("registered! :)");
        }else {
            System.out.println("User cannot be registered!");
        }
    }
}
