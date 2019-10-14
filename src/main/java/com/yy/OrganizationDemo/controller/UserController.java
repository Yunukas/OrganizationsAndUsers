package com.yy.OrganizationDemo.controller;

import com.yy.OrganizationDemo.dto.OrganizationDto;
import com.yy.OrganizationDemo.dto.UserDto;
import com.yy.OrganizationDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // create a single user
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    // get a list of all users
    @GetMapping
    public List<UserDto> getAll(){
        return userService.getAll();
    }

    // read a single user
    @GetMapping(path = "/{userId}")
    public UserDto get(@PathVariable(name = "userId") Long userId){
        return userService.get(userId);
    }

    // read all organizations to which a user belongs
    @GetMapping(path = "/{userId}/organizations")
    public Set<OrganizationDto> getOrg(@PathVariable(name = "userId") Long userId) {
        return userService.getOrgs(userId);
    }

    // update a single user
    @PutMapping(path = "/{userId}")
    public UserDto update(@PathVariable(name = "userId") Long userId,@RequestBody UserDto userDto) {
        userDto.setId(userId);
        return userService.update(userDto);
    }

    // delete a single user
    @DeleteMapping(path = "/{userId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "userId") Long userId) {
        userService.delete(userId);
    }
}
