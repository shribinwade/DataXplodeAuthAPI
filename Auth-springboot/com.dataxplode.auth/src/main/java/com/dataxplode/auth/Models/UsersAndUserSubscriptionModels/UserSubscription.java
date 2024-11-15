package com.dataxplode.auth.Models.UsersAndUserSubscriptionModels;

import javax.persistence.*;

import com.dataxplode.auth.Models.planModel.Plan;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "user_subscriptions")
@Data
public class UserSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Relationship with User

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan; // Relationship with Plan

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private LocalDate nextBillingDate;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Integer searchesUsed = 0;

    // Getters and Setters
}
