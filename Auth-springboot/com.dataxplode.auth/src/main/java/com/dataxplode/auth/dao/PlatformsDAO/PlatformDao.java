package com.dataxplode.auth.dao.PlatformsDAO;

import com.dataxplode.auth.Models.platformsModel.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformDao extends JpaRepository<Platform,Long> {

}
