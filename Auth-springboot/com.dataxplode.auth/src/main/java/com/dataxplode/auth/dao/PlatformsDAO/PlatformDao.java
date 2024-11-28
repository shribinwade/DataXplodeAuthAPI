package com.dataxplode.auth.dao.PlatformsDAO;

import com.dataxplode.auth.Models.pincodeModel.Pincode;
import com.dataxplode.auth.Models.planModel.Plan;
import com.dataxplode.auth.Models.platformsModel.Platform;
import com.dataxplode.auth.wrapper.PlanWrapper;
import com.dataxplode.auth.wrapper.PlatformWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlatformDao extends JpaRepository<Platform,Long> {

    Optional<Platform> findByPlatformName(@Param("platformName") String platformName);
    List<PlatformWrapper> getAllPlatforms();

}
