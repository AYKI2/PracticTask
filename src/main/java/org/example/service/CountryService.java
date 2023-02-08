package org.example.service;

import org.example.model.Countries;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    String saveCountry(Countries countries);
    String saveAllCountries(List<Countries> countries);
    List<Countries> getAllCountries();
    Optional<Countries> findById(Long id);
    Optional<Countries> deleteById(Long id);
    String deleteAllCountries();
    Optional<Countries> updateCountry(Long id, Countries newCountries);
    Optional<Countries> getCountryByLongDescription();
    int countryQuantity(String name);
}
