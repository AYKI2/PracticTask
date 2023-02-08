package org.example.repository;

import org.example.model.Countries;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {
    void saveCountry(Countries countries);
    void saveAllCountries(List<Countries>countries);
    List<Countries> getAllCountries();
    Optional<Countries> findById(Long id);
    Optional<Countries> deleteById(Long id);
    void deleteAllCountries();
    Optional<Countries> updateCountry(Long id, Countries newCountries);
    Optional<Countries> getCountryByLongDescription();
    int countryQuantity(String name);
}
