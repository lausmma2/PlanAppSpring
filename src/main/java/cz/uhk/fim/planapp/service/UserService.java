package cz.uhk.fim.planapp.service;

import cz.uhk.fim.planapp.domain.User;
import cz.uhk.fim.planapp.dto.UserDto;
import cz.uhk.fim.planapp.exceptions.FirstnameIsEmptyException;
import cz.uhk.fim.planapp.exceptions.LastnameIsEmptyException;
import cz.uhk.fim.planapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserByVisibleId(String visibleId, String username){
        return userRepository.findUserByVisibleId(visibleId);
    }

    public User findUserByUsername(String username){
        //Nejspíš není potřeba ošetřovat chyby...
        return userRepository.findByUsername(username);
    }

    public void updateUser(User userInDb, User updatedUser){
        // TODO: 11.02.2020 Dodělat validaci... když pošlu třeba jen firstname, lastname, tak ostatní se nastaví na null... a když někdo smaže firstname, lastname = nullPointerException

        //if(aboutMe = null)...
        userInDb.setFirstname(updatedUser.getFirstname());
        userInDb.setLastname(updatedUser.getLastname());
        userInDb.setPhone(updatedUser.getPhone());
        userInDb.setCountry(updatedUser.getCountry());
        userInDb.setAboutMe(updatedUser.getAboutMe());

        userRepository.save(userInDb);
    }

    /*@Transactional
    public List<UserDto> getAllUsers(){
        List<UserDto> userDtos = new ArrayList<>();
        return userDtos;
    }*/
}
