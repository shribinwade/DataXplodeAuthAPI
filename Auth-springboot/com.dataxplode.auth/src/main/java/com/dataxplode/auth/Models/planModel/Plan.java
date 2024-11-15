package com.dataxplode.auth.Models.planModel;


import com.dataxplode.auth.Models.serviceUnderPlan.ServiceUnderPlan;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;


@NamedQuery(name = "Plan.findByName", query = "select u from Plan u where u.name=:planName")
@NamedQuery(name = "Plan.getAllPlans", query = "select new com.dataxplode.auth.wrapper.PlanWrapper(p.id,p.name,p.searchLimit,p.price,p.description,p.billingCycle,p.createdAt) from Plan p")

@Entity
@Table(name = "Plans")
@Data
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @Column(nullable = false ,unique = true)
    private String name;

    @Column(nullable = false)
    private Integer searchLimit;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false, unique = true)
    private String description;

    @Column(nullable = false)
    private String billingCycle;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "plan")
    private List<UserSubscription> userSubscriptions; // Relationship with UserSubscription

    @OneToMany(mappedBy = "plan")
    private List<ServiceUnderPlan> ServiceUnderPlan; // Relationship with PlanService

    // Getters and Setters
}
