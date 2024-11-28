package com.dataxplode.auth.dao.FeatureDAO;

import com.dataxplode.auth.Models.FeatureModel.Features;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface FeatureDAO extends JpaRepository<Features,Long> {

    Features findByFeature_name(@Param("feature_name") String feature_name);
}
