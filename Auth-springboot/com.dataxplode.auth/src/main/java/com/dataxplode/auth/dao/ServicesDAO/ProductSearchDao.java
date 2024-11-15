package com.dataxplode.auth.dao.ServicesDAO;

import com.dataxplode.auth.Models.serviceModels.ProductSearchData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSearchDao extends JpaRepository<ProductSearchData,Integer> {
}
