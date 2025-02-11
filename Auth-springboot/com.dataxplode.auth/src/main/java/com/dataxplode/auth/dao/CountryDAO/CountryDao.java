package com.dataxplode.auth.dao.CountryDAO;



import com.dataxplode.auth.Models.countryModel.Country;
import com.dataxplode.auth.wrapper.CountryWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CountryDao extends JpaRepository<Country,Integer> {

    List<CountryWrapper> getALlCountries();
    Country getCountryByName(@Param("countryName") String countryName);

}
