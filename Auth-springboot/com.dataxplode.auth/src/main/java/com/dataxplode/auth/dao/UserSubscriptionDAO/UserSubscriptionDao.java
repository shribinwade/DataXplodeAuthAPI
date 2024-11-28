package com.dataxplode.auth.dao.UserSubscriptionDAO;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSubscriptionDao extends JpaRepository<UserSubscription,Long> {

    UserSubscription findByUser_UserId(Long userId);
    UserSubscription findByPlan_PlanId(Long planId);

}
