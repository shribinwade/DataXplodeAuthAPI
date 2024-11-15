package com.dataxplode.auth.wrapper;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;
import com.dataxplode.auth.Models.planModel.Plan;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
public class user_subscripionsWrapper {

    private Long subscriptionId;
    private User user; // Relationship with User
    private Plan plan; // Relationship with Plan
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate nextBillingDate;
    private String status;
    private Integer searchesUsed = 0;
}
