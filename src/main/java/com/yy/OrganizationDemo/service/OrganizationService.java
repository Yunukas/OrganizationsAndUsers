package com.yy.OrganizationDemo.service;

import com.yy.OrganizationDemo.dto.OrganizationDto;
import com.yy.OrganizationDemo.dto.UserDto;

import java.util.List;
import java.util.Set;

public interface OrganizationService {
    OrganizationDto save(OrganizationDto orgDto);

    OrganizationDto update(OrganizationDto orgDto);

    OrganizationDto get(Long orgId);

    List<OrganizationDto> getAll();

    void addUser(Long userId, Long orgId);

    void deleteUser(Long orgId, Long userId);

    Set<UserDto> getUsers(Long orgId);
}
