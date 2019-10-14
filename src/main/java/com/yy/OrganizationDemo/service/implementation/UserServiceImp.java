package com.yy.OrganizationDemo.service.implementation;

import com.yy.OrganizationDemo.dto.OrganizationDto;
import com.yy.OrganizationDemo.dto.UserDto;
import com.yy.OrganizationDemo.entity.Organization;
import com.yy.OrganizationDemo.entity.User;
import com.yy.OrganizationDemo.exceptions.ResourceNotFound;
import com.yy.OrganizationDemo.repository.OrganizationRepository;
import com.yy.OrganizationDemo.repository.UserRepository;
import com.yy.OrganizationDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    // create a single user
    @Override
    public UserDto save(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        userRepository.save(user);
        userDto.setId(user.getId());
        return userDto;
    }

    // update a single user
    @Override
    public UserDto update(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
        return user.getDto();
    }

    // get a single user
    @Override
    public UserDto get(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new ResourceNotFound("User Id-" + userId);
        }
        return user.map(User::getDto).get();
    }

    // get all users from DB
    @Override
    public List<UserDto> getAll() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : userList){
            userDtoList.add(user.getDto());
        }
        return userDtoList;
    }
    // read all organizations to which a user belongs
    @Override
    public Set<OrganizationDto> getOrgs(Long userId) {
        User user = userRepository.getOne(userId);
        Set<OrganizationDto> organizationDtos = new HashSet<>();
        for(Organization organization : user.getOrgs()){
            organizationDtos.add(organization.getDto());
        }
        return organizationDtos;
    }

    // delete a single user
    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

}
