package com.dataxplode.auth.dao.PlanDAO;

import com.dataxplode.auth.Models.planModel.Plan;
import com.dataxplode.auth.wrapper.PlanWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PlanDao extends JpaRepository<Plan,Long> {

    Plan findByName(@Param("planName") String planName);
    List<PlanWrapper> getAllPlans();
//    Plan updateByID(@Param("planId")  Long planId);
    Plan getFreePlan();

}
