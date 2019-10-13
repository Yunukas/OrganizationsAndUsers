package com.yy.OrganizationDemo.repository;

import com.yy.OrganizationDemo.entity.Organization;
import com.yy.OrganizationDemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
