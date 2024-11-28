package com.dataxplode.auth.dao.KeywordSearchDAO;

import com.dataxplode.auth.Models.FeatureContentModel.FeatureContentModel;
import com.dataxplode.auth.Models.FeatureModel.Features;
import com.dataxplode.auth.Models.ReviewsModel.Reviews;
import com.dataxplode.auth.Models.countryModel.Country;
import com.dataxplode.auth.Models.pincodeModel.Pincode;
import com.dataxplode.auth.Models.platformsModel.Platform;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

public interface KeywordSearchDAO extends JpaRepository<FeatureContentModel,Long> {

}
