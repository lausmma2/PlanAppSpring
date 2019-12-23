package cz.uhk.fim.planapp.service;

import cz.uhk.fim.planapp.domain.User;
import cz.uhk.fim.planapp.exceptions.EmailExistsException;
import cz.uhk.fim.planapp.exceptions.UsernameExistsException;
import cz.uhk.fim.planapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    public User saveOrUpdateUser(User user) {
        Long count = userRepository.count() + 100;
        user.setVisibleId(count.toString());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDateTime now = LocalDateTime.now();
        dtf.format(now);
        user.setCreatedOn(now);

        return userRepository.save(user);
    }

    @Transactional
    public void confirmUserByVisibleId(String visibleId){
        User user = userRepository.findUserByVisibleId(visibleId.toString());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDateTime tomorrow = user.getCreatedOn().plusHours(24);
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
