package com.dataxplode.auth.wrapper;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleWrapper {

    private Long roleId;
    private String roleName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
