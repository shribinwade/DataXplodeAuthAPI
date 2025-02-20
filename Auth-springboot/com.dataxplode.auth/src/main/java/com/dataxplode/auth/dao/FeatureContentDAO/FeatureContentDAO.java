package com.dataxplode.auth.dao.FeatureContentDAO;

import com.dataxplode.auth.DTO.UserSearchHistoryDTO;
import com.dataxplode.auth.Models.FeatureContentModel.FeatureContentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureContentDAO extends JpaRepository<FeatureContentModel,Long> {



    @Query("SELECT new com.dataxplode.auth.DTO.UserSearchHistoryDTO(" +
            "fc.id,fc.productQuery, fc.keywordQuery, fc.distributorQuery, fc.competitiorAnalysisQuery, fc.competitiveStratergyQuery, fc.marketSearchQuery , fc.createdAt, c.countryName, f.featureName, p.platformName) " +
            "FROM FeatureContentModel fc " +
            "JOIN fc.feature f " +
            "JOIN UserSubscription us ON us.user.userId = fc.user.userId " +  // Correcting the join condition for UserSubscription
            "JOIN fc.country c " +
            "JOIN fc.platform p " +
            "WHERE us.user.userId = :userId " +
            "AND us.status = 'ACTIVE' " +
            "AND LOWER(f.featureName) = LOWER(:featureName)")  // Ensuring the featureName is case-insensitive
    List<UserSearchHistoryDTO> searchByKeywordAndSubscription(@Param("userId") Long userId,
                                                              @Param("featureName") String featureName);


}
