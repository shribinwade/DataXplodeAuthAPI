package com.dataxplode.auth.Models.serviceModels;

import com.dataxplode.auth.Models.PlanServicePlatform.ServicePlatform;
import com.dataxplode.auth.Models.ReviewsModel.Reviews;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;
import com.dataxplode.auth.Models.countryModel.Country;
import com.dataxplode.auth.Models.pincodeModel.Pincode;
import com.dataxplode.auth.Models.platformsModel.Platform;
import lombok.Data;

import javax.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "keyword_search_data")
@Data
public class KeywordSearchData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_search_id")
    private Integer keywordSearchId;

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

    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false)
    private Reviews review;

    @Column(name = "search_query", nullable = false)
    private String searchQuery;

    @Column(name = "search_results", columnDefinition = "JSON", nullable = false)
    private String searchResults;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    // Getters and Setters
}
