package cz.uhk.fim.planapp.service;

import cz.uhk.fim.planapp.domain.User;
import cz.uhk.fim.planapp.exceptions.EmailExistsException;
import cz.uhk.fim.planapp.exceptions.UsernameExistsException;
import cz.uhk.fim.planapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    static boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public User saveOrUpdateUser(User user) {

        boolean isValid = isValid(user.getEmail());

        if (isValid) {
            try {
                return userRepository.save(user);
            } catch (Exception ex) {
                throw new EmailExistsException("Email: " + "'" + user.getEmail()
                        + "'" + " already exists!");
            }
        }else{
            throw new EmailExistsException("Email: " + "'" + user.getEmail()
                    + "'" + " is not valid!");
        }
    }
}
