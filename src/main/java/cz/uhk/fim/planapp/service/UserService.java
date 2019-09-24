package cz.uhk.fim.planapp.service;

import cz.uhk.fim.planapp.domain.User;
import cz.uhk.fim.planapp.dto.UserDto;
import cz.uhk.fim.planapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<UserDto> getAllUsersBySchoolId(){
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for(User user: users){
            UserDto userDto = new UserDto();
            userDto.setUserId(user.getUserId());
            userDto.setFirstName(user.getFirstname());
            userDto.setLastName(user.getLastname());
            userDto.setEmail(user.getEmail());

            userDtos.add(userDto);
        }

        return userDtos;
    }
}
