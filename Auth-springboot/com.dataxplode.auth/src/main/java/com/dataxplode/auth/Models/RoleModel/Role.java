package com.dataxplode.auth.Models.RoleModel;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Role")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(nullable = false)
    private String roleName;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt =LocalDateTime.now();;

    @OneToMany(mappedBy = "role")
    @JsonBackReference // Mark this as the back part of the relationship
    private List<User> users; // Relationship with User

    // Getters and Setters
}
