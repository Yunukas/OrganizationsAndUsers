package com.yy.OrganizationDemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yy.OrganizationDemo.dto.OrganizationDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "organization")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Organization extends BaseEntity {

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "phone")
    private String phone;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "orgs")
    private Set<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<User> getUsers() {
        return users;
    }

    private Set<Long> getUserIds(){
        Set<Long> userIds = new HashSet<>();
        for(User user : users){
            userIds.add(user.getId());
        }
        return userIds;
    }
    public OrganizationDto getDto(){
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setId(getId());
        organizationDto.setName(getName());
        organizationDto.setAddress((getAddress()));
        organizationDto.setPhone(getPhone());
        organizationDto.setUsers(getUserIds());
        return organizationDto;
    }
}
