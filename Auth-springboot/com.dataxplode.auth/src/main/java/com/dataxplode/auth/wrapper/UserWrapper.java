package com.dataxplode.auth.wrapper;

import com.dataxplode.auth.Models.RoleModel.Role;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWrapper {

    private Long userId;
    private String username;
    private String email;
    private String contactNumber;
    private String userStatus;
    private Role role;
    private UserSubscription subscriptions;
}
