package com.dataxplode.auth.dao.RoleDAO;

import com.dataxplode.auth.Models.RoleModel.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {
}
