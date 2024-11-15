package com.dataxplode.auth.Models.serviceUnderPlan;

import javax.persistence.*;

import com.dataxplode.auth.Models.PlanServicePlatform.ServicePlatform;
import com.dataxplode.auth.Models.planModel.Plan;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "services_under_each_plan")
@Data
public class ServiceUnderPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planServiceId;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan; // Relationship with Plan

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServicePlatform servicePlatform; // Relationship with ServicePlatform

    @Column(nullable = false)
    private Integer usageLimit;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Getters and Setters
}
