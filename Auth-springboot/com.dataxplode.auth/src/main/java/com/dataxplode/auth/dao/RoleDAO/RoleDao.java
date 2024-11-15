package com.dataxplode.auth.dao.RoleDAO;

import com.dataxplode.auth.Models.RoleModel.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
}
