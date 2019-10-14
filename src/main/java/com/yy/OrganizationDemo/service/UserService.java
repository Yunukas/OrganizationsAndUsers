package com.yy.OrganizationDemo.service;

import com.yy.OrganizationDemo.dto.OrganizationDto;
import com.yy.OrganizationDemo.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface UserService {

    UserDto save(UserDto userDto);

    UserDto update(UserDto userDto);

    UserDto get(Long userId);

    List<UserDto> getAll();

    Set<OrganizationDto> getOrgs(Long userId);

    void delete(Long userId);
}
