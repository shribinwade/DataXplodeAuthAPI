package com.dataxplode.auth.Models.FeatureContentModel;

import com.dataxplode.auth.Models.FeatureModel.Features;
import com.dataxplode.auth.Models.ReviewsModel.Reviews;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;
import com.dataxplode.auth.Models.countryModel.Country;
import com.dataxplode.auth.Models.pincodeModel.Pincode;
import com.dataxplode.auth.Models.platformsModel.Platform;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;



@Entity
@Table(name = "Feature_content_table")
@Data
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonBinaryType.class)
})
public class FeatureContentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "feature_id", nullable = false)
    @JsonBackReference
    private Features feature;

    @Type(type = "json")
    @Column(name = "search_results", columnDefinition = "JSON", unique = true)
    private String searchData;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate createdAt;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate updatedAt;

    @Column(name = "keyword_query", nullable = true, unique = true)
    private String keywordQuery;

    @Column(name = "product_query", nullable = true, unique = true)
    private String productQuery;

    @Column(name = "competitive_stratergy_query", nullable = true, unique = true)
    private String competitiveStratergyQuery;

    @Column(name = "market_Search_query", nullable = true, unique = true)
    private String marketSearchQuery;

    @Column(name = "competitior_analysis_query", nullable = true, unique = true)
    private String competitiorAnalysisQuery;

    @Column(name = "distributor_query", nullable = true, unique = true)
    private String distributorQuery;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id", nullable = true)
    private Reviews review;

    @ManyToOne
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pincode_id", nullable = true)
    private Pincode pincode;

    @ManyToOne
    @JoinColumn(name = "user_id")  // Ensures each search is linked to a user
    private User user;




}
