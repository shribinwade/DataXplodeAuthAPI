package com.dataxplode.auth.Models.planModel;


import com.dataxplode.auth.Models.PlanFeatureCommonModel.PlanFeatureTable;
import com.dataxplode.auth.Models.featuresUnderPlan.FeaturesUnderPlan;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;


@NamedQuery(name = "Plan.findByName", query = "select u from Plan u where u.planname=:planName")
@NamedQuery(name = "Plan.getAllPlans", query = "select new com.dataxplode.auth.wrapper.PlanWrapper(p.id,p.planname,p.searchLimit,p.planprice,p.description,p.billingCycle,p.createdAt) from Plan p")
@NamedQuery(name = "Plan.getFreePlan", query = "SELECT u FROM Plan u WHERE u.planname = 'free'")
@Entity
@Table(name = "Plans")
@Data
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @Column(nullable = false ,unique = true)
    private String planname;

    @Column(nullable = false)
    private Integer searchLimit;

    @Column(nullable = false)
    private double planprice;

    @Column(nullable = false, unique = true)
    private String description;

    @Column(nullable = false)
    private BillingCycle billingCycle = BillingCycle.FREE;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "plan")
    @JsonBackReference // Prevents infinite recursion
    private List<UserSubscription> userSubscriptions; // Relationship with UserSubscription

    @OneToMany(mappedBy = "plan")
    private List<PlanFeatureTable> planFeature;

//    @OneToMany(mappedBy = "plan")
//    @JsonBackReference // Prevents infinite recursion
//    private List<FeaturesUnderPlan> FeaturesUnderPlan; // Relationship with PlanService


    // Getters and Setters
}
