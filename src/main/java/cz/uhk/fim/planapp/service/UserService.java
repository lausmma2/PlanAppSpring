package cz.uhk.fim.planapp.service;

import cz.uhk.fim.planapp.domain.User;
import cz.uhk.fim.planapp.exceptions.FirstnameIsEmptyException;
import cz.uhk.fim.planapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserByVisibleId(String visibleId, String username){
        return userRepository.findUserByVisibleId(visibleId);
    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void updateUser(User userInDb, User updatedUser){
        if((updatedUser.getFirstname().equals("") && updatedUser.getLastname().equals(""))
                || updatedUser.getFirstname().equals("")
                || updatedUser.getLastname().equals("")) {
            throw new FirstnameIsEmptyException("Firstname or Lastname cannot be blank!");
        }else{
            userInDb.setFirstname(updatedUser.getFirstname());
            userInDb.setLastname(updatedUser.getLastname());
            userInDb.setPhone(updatedUser.getPhone());
            userInDb.setCountry(updatedUser.getCountry());
            userInDb.setAboutMe(updatedUser.getAboutMe());
        }

        userRepository.save(userInDb);
    }
}
