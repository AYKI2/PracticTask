package org.example.service;

import org.example.model.Countries;
import org.example.repository.CountryRepository;
import org.example.repository.CountryRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class CountryServiceImpl implements CountryService {
    private CountryRepository cr = new CountryRepositoryImpl();

    @Override
    public String saveCountry(Countries countries) {
        cr.saveCountry(countries);
        return "Successfully saved!";
    }

    @Override
    public String saveAllCountries(List<Countries> countries) {
        cr.saveAllCountries(countries);
        return "All successfully saved!";
    }

    @Override
    public List<Countries> getAllCountries() {
        return cr.getAllCountries();
    }

    @Override
    public Optional<Countries> findById(Long id) {
        return cr.findById(id);
    }

    @Override
    public Optional<Countries> deleteById(Long id) {
        return cr.deleteById(id);
    }

    @Override
    public String deleteAllCountries() {
        cr.deleteAllCountries();
        return "Successfully cleaned!";
    }

    @Override
    public Optional<Countries> updateCountry(Long id, Countries newCountries) {
        return cr.updateCountry(id, newCountries);
    }

    @Override
    public Optional<Countries> getCountryByLongDescription() {
        return cr.getCountryByLongDescription();
    }

    @Override
    public int countryQuantity(String name) {
        return cr.countryQuantity(name);
    }
}
