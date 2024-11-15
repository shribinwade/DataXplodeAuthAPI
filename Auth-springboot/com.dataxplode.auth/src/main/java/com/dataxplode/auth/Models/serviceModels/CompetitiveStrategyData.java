package com.dataxplode.auth.Models.serviceModels;

import com.dataxplode.auth.Models.PlanServicePlatform.ServicePlatform;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;
import com.dataxplode.auth.Models.countryModel.Country;
import com.dataxplode.auth.Models.pincodeModel.Pincode;
import com.dataxplode.auth.Models.platformsModel.Platform;
import lombok.Data;

import javax.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "competitive_stratergy_data")
@Data

public class CompetitiveStrategyData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competitive_stratergy_id")
    private Integer competitiveStrategyId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServicePlatform service;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "pincode_id", nullable = false)
    private Pincode pincode;

    @ManyToOne
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;

    @Column(name = "search_query", nullable = false)
    private String searchQuery;

    @Column(name = "search_results", columnDefinition = "JSON", nullable = false)
    private String searchResults;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    // Getters and Setters
}
