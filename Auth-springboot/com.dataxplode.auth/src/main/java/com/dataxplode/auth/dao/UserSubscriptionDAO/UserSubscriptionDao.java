package com.dataxplode.auth.dao.UserSubscriptionDAO;

import com.dataxplode.auth.Models.FeatureContentModel.FeatureContentModel;
import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.UserSubscription;
import com.dataxplode.auth.wrapper.UserSearchDataWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserSubscriptionDao extends JpaRepository<UserSubscription,Long> {

    UserSubscription findByUser_UserId(Long userId);
    UserSubscription findByPlan_PlanId(Long planId);


    //Retrive Data for Keyword
    @Query("SELECT fcm.searchData " +
            "FROM UserSubscription us " +
            "JOIN us.plan p " +
            "JOIN p.planFeature pf " +
            "JOIN pf.feature f " +
            "JOIN f.content fcm " +
            "WHERE fcm.user.userId = :userId " +
            "AND fcm.country.countryName = :country " +
            "AND fcm.keywordQuery = :keyword")
    Optional<String> findSearchDataByUserAndCountryAndKeyword(
            @Param("userId") Long userId,
            @Param("country") String country,
            @Param("keyword") String keyword);



    //Retrive Data for Distributor
    @Query("SELECT fcm.searchData " +
            "FROM UserSubscription us " +
            "JOIN us.plan p " +
            "JOIN p.planFeature pf " +
            "JOIN pf.feature f " +
            "JOIN f.content fcm " +
            "WHERE us.user.userId = :userId " +
            "AND fcm.country.countryName = :country " +
            "AND fcm.distributorQuery = :distributor")
    Optional<String> findSearchDataByUserAndCountryAndDistributor(
            @Param("userId") Long userId,
            @Param("country") String country,
            @Param("distributor") String distributor);


    //Retrive Data for Market
    @Query("SELECT fcm.searchData " +
            "FROM UserSubscription us " +
            "JOIN us.plan p " +
            "JOIN p.planFeature pf " +
            "JOIN pf.feature f " +
            "JOIN f.content fcm " +
            "WHERE us.user.userId = :userId " +
            "AND fcm.country.countryName = :country " +
            "AND fcm.marketSearchQuery = :market")
    Optional<String> findSearchDataByUserAndCountryAndMarket(
            @Param("userId") Long userId,
            @Param("country") String country,
            @Param("market") String market);


    //Retrive Data for CompetitveStratergy
    @Query("SELECT fcm.searchData " +
            "FROM UserSubscription us " +
            "JOIN us.plan p " +
            "JOIN p.planFeature pf " +
            "JOIN pf.feature f " +
            "JOIN f.content fcm " +
            "WHERE us.user.userId = :userId " +
            "AND fcm.country.countryName = :country " +
            "AND fcm.competitiveStratergyQuery = :competitiveStratergy")
    Optional<String> findSearchDataByUserAndCountryAndCompetitiveStratergy(
            @Param("userId") Long userId,
            @Param("country") String country,
            @Param("competitiveStratergy") String competitiveStratergy
    );


    //Retrive Data for CompetitorAnalysis
    @Query("SELECT fcm.searchData " +
            "FROM UserSubscription us " +
            "JOIN us.plan p " +
            "JOIN p.planFeature pf " +
            "JOIN pf.feature f " +
            "JOIN f.content fcm " +
            "WHERE us.user.userId = :userId " +
            "AND fcm.country.countryName = :country " +
            "AND fcm.competitiorAnalysisQuery = :competitorAnalysis")
    Optional<String> findSearchDataByUserAndCountryAndCompetitorAnalysisQuery(
            @Param("userId") Long userId,
            @Param("country") String country,
            @Param("competitorAnalysis") String competitorAnalysis
    );



    //Retrive Data for Product
    @Query("SELECT fcm.searchData " +
            "FROM UserSubscription us " +
            "JOIN us.plan p " +
            "JOIN p.planFeature pf " +
            "JOIN pf.feature f " +
            "JOIN f.content fcm " +
            "WHERE us.user.userId = :userId " +
            "AND fcm.country.countryName = :country " +
            "AND fcm.productQuery = :product")
    Optional<String> findSearchDataByUserAndCountryAndProductQuery(
            @Param("userId") Long userId,
            @Param("country") String country,
            @Param("product") String product
    );


//    reviewResult

    //Retrive Data for Product
    @Query("SELECT r.reviewResult " +
            "FROM UserSubscription us " +
            "JOIN us.plan p " +
            "JOIN p.planFeature pf " +
            "JOIN pf.feature f " +
            "JOIN f.content fcm " +
            "JOIN fcm.review r " +
            "WHERE us.user.userId = :userId " +
            "AND fcm.country.countryName = :country " +
            "AND fcm.productQuery = :product")
    Optional<String> findProductReviewByProductQuery(
            @Param("userId") Long userId,
            @Param("country") String country,
            @Param("product") String product
    );

//    //Retrive UserSearchData
//    @Query("SELECT "+
//            "COUNT(fct.competitiorAnalysisQuery),  " +
//            "COUNT(fct.competitiveStratergyQuery),  " +
//            "COUNT(fct.distributorQuery),  " +
//            "COUNT(fct.keywordQuery),  " +
//            "COUNT(fct.marketSearchQuery),  " +
//            "COUNT(fct.productQuery) " +
//            "FROM UserSubscription us " +
//            "JOIN us.plan p " +
//            "JOIN p.planFeature pf " +
//            "JOIN pf.feature f " +
//            "JOIN f.content fct " +
//            "WHERE us.user.userId = :userId "+
//            "AND fct.country.countryName = :country "+
//            "AND fct.platform.platformName = :platform"
//    )

    @Query("SELECT new com.dataxplode.auth.wrapper.UserSearchDataWrapper(" +
            "us.user.userId, " +
            "fct.country.countryName," +
            "fct.platform.platformName," +
            "COUNT(fct.competitiorAnalysisQuery), " +
            "COUNT(fct.competitiveStratergyQuery),  " +
            "COUNT(fct.distributorQuery),  " +
            "COUNT(fct.keywordQuery),  " +
            "COUNT(fct.marketSearchQuery),  " +
            "COUNT(fct.productQuery) " +
            ") " +

            "FROM UserSubscription us " +
            "JOIN us.plan p " +
            "JOIN p.planFeature pf " +
            "JOIN pf.feature f " +
            "JOIN f.content fct " +
            "WHERE fct.user.userId = :userId " +
            "AND fct.country.countryName = :country " +
            "AND fct.platform.platformName = :platform "+
            "GROUP BY us.user.userId, fct.country.countryName, fct.platform.platformName")
    List<UserSearchDataWrapper> findUserSearchDataQuery(@Param("userId") Long userId,
                                                        @Param("country") String country,
                                                        @Param("platform") String platform);




}







