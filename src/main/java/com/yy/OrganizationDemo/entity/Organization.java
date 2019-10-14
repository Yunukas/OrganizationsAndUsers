package com.yy.OrganizationDemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yy.OrganizationDemo.dto.OrganizationDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "organizations")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Organization extends BaseEntity {

    @NotNull
    @Column(name = "name", unique = true)
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

    // this method is used to get the ids of users who belong to a specific organization
    private Set<Long> getUserIds(){
        Set<Long> userIds = new HashSet<>();
        for(User user : users){
            userIds.add(user.getId());
        }
        return userIds;
    }
    // create the organization dto object to be displayed
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
