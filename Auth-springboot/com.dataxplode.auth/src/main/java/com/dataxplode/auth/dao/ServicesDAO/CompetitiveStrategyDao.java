package com.dataxplode.auth.dao.ServicesDAO;

import com.dataxplode.auth.Models.serviceModels.CompetitiveStrategyData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitiveStrategyDao extends JpaRepository<CompetitiveStrategyData,Integer> {
}
