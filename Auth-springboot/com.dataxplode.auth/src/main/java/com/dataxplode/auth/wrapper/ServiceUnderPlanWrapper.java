package com.dataxplode.auth.wrapper;

import com.dataxplode.auth.Models.PlanServicePlatform.ServicePlatform;
import com.dataxplode.auth.Models.planModel.Plan;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ServiceUnderPlanWrapper {
    private Long planServiceId;
    private Plan plan;       // Relationship with Plan
    private ServicePlatform servicePlatform; // Relationship with ServicePlatform
    private Integer usageLimit;
    private LocalDateTime createdAt;
}
