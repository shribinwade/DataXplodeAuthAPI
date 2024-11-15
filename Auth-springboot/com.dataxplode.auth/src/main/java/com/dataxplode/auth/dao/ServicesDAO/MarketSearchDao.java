package com.dataxplode.auth.dao.ServicesDAO;

import com.dataxplode.auth.Models.serviceModels.MarketSearchData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketSearchDao extends JpaRepository<MarketSearchData,Integer> {
}
