package com.dataxplode.auth.Models.UsersAndUserSubscriptionModels;

import javax.persistence.*;

import com.dataxplode.auth.Models.planModel.Plan;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.time.LocalDate;


@Entity
@Table(name = "user_subscriptions")
@Data
public class UserSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private LocalDate nextBillingDate;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user; // Relationship with User

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    @JsonBackReference
    private Plan plan; // Relationship with Plan

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    // Getters and Setters
}
