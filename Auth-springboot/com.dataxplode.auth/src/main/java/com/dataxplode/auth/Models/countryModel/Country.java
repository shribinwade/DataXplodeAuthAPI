package com.dataxplode.auth.Models.countryModel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@NamedQuery(name="Country.getALlCountries", query = "select new com.dataxplode.auth.wrapper.CountryWrapper(u.countryId, u.countryName) from Country u")

@Entity
@Data
@Getter
@Setter
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "country_name", nullable = false, unique = true)
    private String countryName;

    // Getters and Setters
}
