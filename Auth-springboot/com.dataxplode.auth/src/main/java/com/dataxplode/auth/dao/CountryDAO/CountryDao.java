package com.dataxplode.auth.dao.CountryDAO;



import com.dataxplode.auth.Models.countryModel.Country;
import com.dataxplode.auth.wrapper.CountryWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryDao extends JpaRepository<Country,Integer> {

    List<CountryWrapper> getALlCountries();
    Country getCountryByName(@Param("countryName") String countryName);

}
