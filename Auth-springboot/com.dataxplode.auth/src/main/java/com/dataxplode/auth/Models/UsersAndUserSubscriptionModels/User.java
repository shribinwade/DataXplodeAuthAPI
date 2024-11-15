package com.dataxplode.auth.Models.UsersAndUserSubscriptionModels;


import com.dataxplode.auth.Models.RoleModel.Role;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@NamedQuery(name = "User.findByEmailId", query = "select u from User u where u.email=:email")
@NamedQuery(name="User.getAllUser", query = "select new com.dataxplode.auth.wrapper.UserWrapper(u.id, u.username,u.email,u.contactNumber,u.userStatus) from User u where u.role= 'user'")
@NamedQuery(name="User.updateStatus",query = "update User u set u.userStatus=:userStatus where u.id=:id")
@NamedQuery(name="User.getAllAdmin", query = "select u.email from User u where u.role= 'admin'")


@Entity
@Data
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {

//    WE have used Data annotation to create noarg arg construtor and getter and setter methods

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;


    @Column(nullable = false ,unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(name = "contactNumber",nullable = false ,unique = true)
    private String contactNumber;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role; // Relationship with Role

    @Column(nullable = false)
    private String userStatus;

    @Column(nullable = true)
    private String verificationToken;

    @Column(nullable = false)
    private boolean enabled;

    @OneToMany(mappedBy = "user")
    private List<UserSubscription> subscriptions; // Relationship with UserSubscription




}
