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

    @Override
    public OrganizationDto update(OrganizationDto orgDto) {
        Organization organization = organizationRepository.getOne(orgDto.getId());

        if(organization == null){
            throw new ResourceNotFound("Organization Id-" + orgDto.getId());
        }

        organization.setName(orgDto.getName());
        organization.setAddress(orgDto.getAddress());
        organization.setPhone(orgDto.getPhone());

        organizationRepository.save(organization);
        return organization.getDto();
    }

    @Override
    public OrganizationDto get(Long orgId) {
        Optional<Organization> organization = organizationRepository.findById(orgId);
        if (!organization.isPresent()) {
            throw new ResourceNotFound("Organization Id-" + orgId);
        }
        return organization.map(Organization::getDto).get();
    }

    @Override
    public List<OrganizationDto> getAll() {
        List<Organization> organizationList = organizationRepository.findAll();
        if (organizationList == null) {
            throw new ResourceNotFound("No organization exists.");
        }
        List<OrganizationDto> organizationDtoList = new ArrayList<>();

        for(Organization org : organizationList){
            organizationDtoList.add(org.getDto());
        }
        return organizationDtoList;
    }

    @Override
    public Set<UserDto> getUsers(Long orgId) {
        Organization organization = organizationRepository.getOne(orgId);
        if(organization == null){
            throw new ResourceNotFound("Organization Id-" + orgId);
        }
        Set<UserDto> userDtos = new HashSet<>();

        for(User user : organization.getUsers()){
            userDtos.add(user.getDto());
        }
        return userDtos;
    }

    @Override
    public void addUser(Long userId, Long orgId) {
        User user = userRepository.getOne(userId);
        Organization org = organizationRepository.getOne(orgId);
        if(user == null) {
            throw new ResourceNotFound("User Id-" + userId);
        }
        if(org == null){
            throw new ResourceNotFound("Organization Id-" + orgId);
        }
        user.getOrgs().add(org);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long orgId, Long userId) {
        User user = userRepository.getOne(userId);
        Organization org = organizationRepository.getOne(orgId);

        if(user == null){
            throw new ResourceNotFound("User Id-" + userId);
        }
        if(org == null) {
            throw new ResourceNotFound("Organization Id-" + orgId);
        }

        if(!org.getUsers().contains(user)){
            throw new ResourceNotFound("Organization Id-" + orgId + " does not have User Id-" + userId);
        }

        user.getOrgs().remove(org);
        org.getUsers().remove(user);
        userRepository.save(user);
    }

    @Override
    public void delete(Long orgId) {
        organizationRepository.deleteById(orgId);
    }
}