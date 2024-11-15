package com.dataxplode.auth.dao.ServicesDAO;

import com.dataxplode.auth.Models.serviceModels.KeywordSearchData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordSearchDao extends JpaRepository<KeywordSearchData,Integer> {
}
