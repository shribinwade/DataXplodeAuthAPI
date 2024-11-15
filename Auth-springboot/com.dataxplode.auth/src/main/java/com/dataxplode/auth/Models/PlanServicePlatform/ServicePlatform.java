package com.dataxplode.auth.Models.PlanServicePlatform;

import javax.persistence.*;

import com.dataxplode.auth.Models.serviceUnderPlan.ServiceUnderPlan;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "service_platform")
@Data
public class ServicePlatform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    @Column(nullable = false)
    private String serviceName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "servicePlatform")
    private List<ServiceUnderPlan> planServices; // Relationship with PlanService

    // Getters and Setters
}
