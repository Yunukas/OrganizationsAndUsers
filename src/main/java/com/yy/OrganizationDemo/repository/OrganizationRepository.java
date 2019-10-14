package com.yy.OrganizationDemo.repository;

import com.yy.OrganizationDemo.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
