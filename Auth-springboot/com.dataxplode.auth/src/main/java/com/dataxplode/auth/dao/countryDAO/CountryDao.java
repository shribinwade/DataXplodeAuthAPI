package com.dataxplode.auth.dao.countryDAO;



import com.dataxplode.auth.Models.countryModel.Country;
import com.dataxplode.auth.wrapper.CountryWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryDao extends JpaRepository<Country,Integer> {

    List<CountryWrapper> getALlCountries();

}
