package com.yy.OrganizationDemo.service.implementation;

import com.yy.OrganizationDemo.dto.OrganizationDto;
import com.yy.OrganizationDemo.dto.UserDto;
import com.yy.OrganizationDemo.entity.Organization;
import com.yy.OrganizationDemo.entity.User;
import com.yy.OrganizationDemo.exceptions.ResourceNotFound;
import com.yy.OrganizationDemo.repository.OrganizationRepository;
import com.yy.OrganizationDemo.repository.UserRepository;
import com.yy.OrganizationDemo.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class OrganizationServiceImp implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private UserRepository userRepository;

    // create a single organization
    @Override
    public OrganizationDto save(OrganizationDto orgDto) {
        Organization organization = new Organization();
        organization.setName(orgDto.getName());
        organization.setAddress(orgDto.getAddress());
        organization.setPhone(orgDto.getPhone());
        organizationRepository.save(organization);
        orgDto.setId(organization.getId());
        return orgDto;
    }

    // update a single organization
    @Override
    public OrganizationDto update(OrganizationDto orgDto) {
        Organization organization = organizationRepository.getOne(orgDto.getId());
        organization.setName(orgDto.getName());
        organization.setAddress(orgDto.getAddress());
        organization.setPhone(orgDto.getPhone());
        organizationRepository.save(organization);
        return organization.getDto();
    }

    // get a single organization from DB
    @Override
    public OrganizationDto get(Long orgId) {
        Optional<Organization> organization = organizationRepository.findById(orgId);
        if (!organization.isPresent()) {
            throw new ResourceNotFound("Organization Id-" + orgId);
        }
        return organization.map(Organization::getDto).get();
    }

    // get the list of all organizations in DB
    @Override
    public List<OrganizationDto> getAll() {
        List<Organization> organizationList = organizationRepository.findAll();
        List<OrganizationDto> organizationDtoList = new ArrayList<>();
        for(Organization org : organizationList){
            organizationDtoList.add(org.getDto());
        }
        return organizationDtoList;
    }

    // read all users who belong to a specific organization
    @Override
    public Set<UserDto> getUsers(Long orgId) {
        Organization organization = organizationRepository.getOne(orgId);
        Set<UserDto> userDtos = new HashSet<>();

        for(User user : organization.getUsers()){
            userDtos.add(user.getDto());
        }
        return userDtos;
    }
    // add a user to an organization
    @Override
    public void addUser(Long userId, Long orgId) {
        User user = userRepository.getOne(userId);
        Organization org = organizationRepository.getOne(orgId);
        user.getOrgs().add(org);
        userRepository.save(user);
    }
    // delete a user from an organization
    @Override
    public void deleteUser(Long orgId, Long userId) {
        User user = userRepository.getOne(userId);
        Organization org = organizationRepository.getOne(orgId);

        if(!org.getUsers().contains(user)){
            throw new ResourceNotFound(" No User Id-" + userId + " found in Organization Id-" + orgId);
        }
        // remove the organization from the collection
        user.getOrgs().remove(org);
        // update the DB
        userRepository.save(user);
    }
}