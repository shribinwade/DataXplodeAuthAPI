package com.dataxplode.auth.DTO;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.SubscriptionStatus;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class userSubscriptionDTO {

    private Long subscriptionId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate nextBillingDate;
    private SubscriptionStatus status;
    private PlanDTO plan;
}
