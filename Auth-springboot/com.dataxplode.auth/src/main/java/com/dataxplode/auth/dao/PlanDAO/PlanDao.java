package com.dataxplode.auth.dao.PlanDAO;

import com.dataxplode.auth.Models.planModel.Plan;
import com.dataxplode.auth.wrapper.PlanWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanDao extends JpaRepository<Plan,Long> {

    Plan findByName(@Param("planName") String planName);
    List<PlanWrapper> getAllPlans();
//    Plan updateByID(@Param("planId")  Long planId);
    Plan getFreePlan();

}
