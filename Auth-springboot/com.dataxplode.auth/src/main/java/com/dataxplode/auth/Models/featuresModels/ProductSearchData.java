package com.dataxplode.auth.Models.featuresModels;

import com.dataxplode.auth.Models.PlanServicePlatform.ServicePlatform;
import com.dataxplode.auth.Models.ReviewsModel.Reviews;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;
import com.dataxplode.auth.Models.countryModel.Country;
import com.dataxplode.auth.Models.pincodeModel.Pincode;
import com.dataxplode.auth.Models.platformsModel.Platform;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "product_search_data")
//@Data
//@TypeDefs({
//        @TypeDef(name = "json", typeClass = JsonBinaryType.class)
//})
public class ProductSearchData {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "product_search_id")
//    private Long productSearchId;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "service_id", nullable = false)
//    private ServicePlatform service;
//
//    @ManyToOne
//    @JoinColumn(name = "country_id", nullable = false)
//    private Country country;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "pincode_id", nullable = false)
//    private Pincode pincode;
//
//    @ManyToOne
//    @JoinColumn(name = "platform_id", nullable = false)
//    private Platform platform;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "review_id", nullable = false)
//    private Reviews review;
//
//    @Column(name = "search_query", nullable = false)
//    private String searchQuery;
//
//    @Type(type = "json")
//    @Column(name = "search_results", columnDefinition = "JSON", nullable = false)
//    private String searchResults;
//
//    @Column(name = "created_at", nullable = true, columnDefinition = "DATE")
//    private LocalDateTime createdAt;

    // Getters and Setters
}
