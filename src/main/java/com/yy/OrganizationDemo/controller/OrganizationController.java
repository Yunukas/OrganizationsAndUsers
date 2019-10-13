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

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody OrganizationDto createOrg(@RequestBody OrganizationDto orgDto) {
        return organizationService.save(orgDto);
    }

    @GetMapping
    public List<OrganizationDto> getAll() {
        return organizationService.getAll();
    }

    @GetMapping(path = "/{orgId}")
    public @ResponseBody OrganizationDto getOrg(@PathVariable(name = "orgId") Long orgId) {
        return organizationService.get(orgId);
    }

    @GetMapping(path = "/{orgId}/users")
    public Set<UserDto> getUsers(@PathVariable(name = "orgId") Long orgId) {
        return organizationService.getUsers(orgId);
    }

    @PutMapping(path = "/{orgId}/users/add")
    @ResponseStatus(value = HttpStatus.OK)
    public void addUser(@PathVariable(name = "orgId") Long orgId,@RequestParam("id") Long userId) {
        organizationService.addUser(userId, orgId);
    }

    @PutMapping(path = "/{orgId}")
    public @ResponseBody OrganizationDto updateOrg(@PathVariable(name = "orgId") Long orgId,@RequestBody OrganizationDto orgDto) {
        orgDto.setId(orgId);
        return organizationService.update(orgDto);
    }

    @DeleteMapping(path = "/{orgId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteOrg(@PathVariable(name = "orgId") Long orgId) {
        organizationService.delete(orgId);
    }

    @DeleteMapping(path = "/{orgId}/users/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void  deleteUser(@PathVariable(name = "orgId") Long orgId, @RequestParam(name = "id") Long userId) {
        organizationService.deleteUser(orgId, userId);
    }

}
