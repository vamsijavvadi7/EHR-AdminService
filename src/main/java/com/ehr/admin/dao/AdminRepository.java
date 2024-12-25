package com.ehr.admin.dao;


import com.ehr.admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUserid(Long userid);

    Optional<Admin> getAdminById(Long id);
    // You can add custom query methods if needed
}
