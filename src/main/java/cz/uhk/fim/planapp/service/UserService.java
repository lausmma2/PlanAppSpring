package cz.uhk.fim.planapp.service;

import cz.uhk.fim.planapp.domain.User;
import cz.uhk.fim.planapp.dto.UserDto;
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

    public UserDto getUserById(Integer userId){
        User user = userRepository.findUserByUserId(userId);
        UserDto userDto = new UserDto();

        userDto.setFirstName(user.getFirstname());
        userDto.setLastName(user.getLastname());
        userDto.setEmail(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setPhone(user.getPhone());
        userDto.setCreatedOn(user.getCreated_On());

        return userDto;
    }

    @Transactional
    public List<UserDto> getAllUsers(){
        List<UserDto> userDtos = new ArrayList<>();
        return userDtos;
    }
}
