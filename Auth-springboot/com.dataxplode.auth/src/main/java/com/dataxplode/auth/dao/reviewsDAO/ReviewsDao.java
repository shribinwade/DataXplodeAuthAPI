package com.dataxplode.auth.dao.reviewsDAO;

import com.dataxplode.auth.Models.ReviewsModel.Reviews;
import com.dataxplode.auth.Models.pincodeModel.Pincode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewsDao extends JpaRepository<Reviews,Long> {

    Optional<Reviews> findByreviewResult(String Reviews);

}
