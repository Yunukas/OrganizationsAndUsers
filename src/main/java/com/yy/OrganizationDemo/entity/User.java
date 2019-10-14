package com.yy.OrganizationDemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yy.OrganizationDemo.dto.UserDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @NotNull
    @Column(name = "firstname")
    private String firstName;

    @NotNull
    @Column(name = "lastname")
    private String lastName;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "phone")
    private String phone;

    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
        CascadeType.PERSIST,
                CascadeType.MERGE
    })
    @JoinTable(name = "org_users",
        joinColumns = {@JoinColumn(name = "user_id")},
        inverseJoinColumns = {@JoinColumn(name = "org_id")})
    @JsonIgnoreProperties("users")
    private Set<Organization> orgs;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<Organization> getOrgs() {
        return orgs;
    }

    // this method is used to get the organization ids to which a user belongs
    private Set<Long> getOrgIds(){
        Set<Long> orgIds = new HashSet<>();
        for(Organization organization : orgs){
            orgIds.add(organization.getId());
        }
        return orgIds;
    }
    // create the user dto object to be displayed
    public UserDto getDto() {
        UserDto dto = new UserDto();
        dto.setId(getId());
        dto.setOrganizations(getOrgIds());
        dto.setFirstName(getFirstName());
        dto.setLastName(getLastName());
        dto.setAddress(getAddress());
        dto.setEmail(getEmail());
        dto.setPhone(getPhone());
        return dto;
    }
}
