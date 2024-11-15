package com.dataxplode.auth.dao.ServicesDAO;

import com.dataxplode.auth.Models.serviceModels.DistributorSearchData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistributorSearchDao extends JpaRepository<DistributorSearchData,Integer> {
}
