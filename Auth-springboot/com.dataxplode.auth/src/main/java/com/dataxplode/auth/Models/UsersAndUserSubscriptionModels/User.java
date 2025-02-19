package com.dataxplode.auth.Models.UsersAndUserSubscriptionModels;


import com.dataxplode.auth.Models.FeatureContentModel.FeatureContentModel;
import com.dataxplode.auth.Models.RoleModel.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@NamedQuery(name = "User.findByEmailId", query = "select u from User u where u.email=:email")
@NamedQuery(name="User.getAllUser", query = "select u from User u where u.role= 2L")
@NamedQuery(name="User.updateStatus",query = "update User u set u.userStatus=:userStatus where u.id=:id")
@NamedQuery(name="User.getAllAdmin", query = "select u.email from User u where u.role= 'admin'")


@Entity
@Data
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Table(name = "users")
@Getter
@Setter
public class User  {
//    WE have used Data annotation to create noarg arg construtor and getter and setter methods

//    private static final long serialVersionUID = 1L;

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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role; // Relationship with Role

    @Column(nullable = false)
    private String userStatus;

    @Column(nullable = true)
    private String verificationToken;

    @Column(nullable = false)
    private boolean enabled;

    @OneToOne(mappedBy = "user" ,fetch = FetchType.LAZY)
    @JsonManagedReference
    private UserSubscription subscriptions; // Relationship with UserSubscription

}
