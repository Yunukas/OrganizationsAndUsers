package com.yy.OrganizationDemo.dto;

import java.util.HashSet;
import java.util.Set;

public class OrganizationDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Set<Long> users;

    public OrganizationDto(){ users = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setUsers(Set<Long> users) {
        this.users = users;
    }

    public Set<Long> getUsers() {
        return users;
    }
}
