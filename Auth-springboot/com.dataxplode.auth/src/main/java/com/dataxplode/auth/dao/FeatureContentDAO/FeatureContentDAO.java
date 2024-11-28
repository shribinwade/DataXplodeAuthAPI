package com.dataxplode.auth.dao.FeatureContentDAO;

import com.dataxplode.auth.Models.FeatureContentModel.FeatureContentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface FeatureContentDAO extends JpaRepository<FeatureContentModel,Long> {

}
