package com.dataxplode.auth.dao.ServicesDAO;

import com.dataxplode.auth.Models.serviceModels.CompetitorAnalysisData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitorAnalysisDao extends JpaRepository<CompetitorAnalysisData,Integer> {
}
