package com.yy.OrganizationDemo.controller;

import com.yy.OrganizationDemo.dto.OrganizationDto;
import com.yy.OrganizationDemo.dto.UserDto;
import com.yy.OrganizationDemo.service.OrganizationService;
import com.yy.OrganizationDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserService userService;

    // create an organization
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public OrganizationDto createOrg(@RequestBody OrganizationDto orgDto) {
        return organizationService.save(orgDto);
    }
    // get a list of all organizations
    @GetMapping
    public List<OrganizationDto> getAll() {
        return organizationService.getAll();
    }
    // read a single organization
    @GetMapping(path = "/{orgId}")
    public OrganizationDto getOrg(@PathVariable(name = "orgId") Long orgId) {
        return organizationService.get(orgId);
    }
    // read all users who belong to a specific organization
    @GetMapping(path = "/{orgId}/users")
    public Set<UserDto> getUsers(@PathVariable(name = "orgId") Long orgId) {
        return organizationService.getUsers(orgId);
    }

    // add a user to an organization
    @PutMapping(path = "/{orgId}/users/add")
    public void addUser(@PathVariable(name = "orgId") Long orgId,@RequestParam("id") Long userId) {
        organizationService.addUser(userId, orgId);
    }
    // update an organization
    @PutMapping(path = "/{orgId}")
    public OrganizationDto updateOrg(@PathVariable(name = "orgId") Long orgId,@RequestBody OrganizationDto orgDto) {
        orgDto.setId(orgId);
        return organizationService.update(orgDto);
    }
    // delete a user from an organization
    @DeleteMapping(path = "/{orgId}/users/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void  deleteUser(@PathVariable(name = "orgId") Long orgId, @RequestParam(name = "id") Long userId) {
        organizationService.deleteUser(orgId, userId);
    }

}
