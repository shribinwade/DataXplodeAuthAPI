package com.dataxplode.auth.dao.reviewsDAO;

import com.dataxplode.auth.Models.ReviewsModel.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsDao extends JpaRepository<Reviews,Integer> {
}
