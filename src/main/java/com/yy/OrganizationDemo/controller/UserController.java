package com.yy.OrganizationDemo.controller;

import com.yy.OrganizationDemo.dto.OrganizationDto;
import com.yy.OrganizationDemo.dto.UserDto;
import com.yy.OrganizationDemo.entity.User;
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

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody UserDto createUser(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    @GetMapping
    public List<UserDto> getAll(){
        return userService.getAll();
    }

    @GetMapping(path = "/{userId}")
    public UserDto get(@PathVariable(name = "userId") Long userId){
        return userService.get(userId);
    }

    @GetMapping(path = "/{userId}/organizations")
    public Set<OrganizationDto> getOrg(@PathVariable(name = "userId") Long userId) {
        return userService.getOrgs(userId);
    }

    @DeleteMapping(path = "/{userId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "userId") Long userId) {
        userService.delete(userId);
    }
}
