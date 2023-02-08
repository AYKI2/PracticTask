package org.example.service;

import org.example.model.Address;
import org.example.repository.AddressRepository;
import org.example.repository.AddressRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class AddressServiceImpl implements AddressService {
    private AddressRepository repository = new AddressRepositoryImpl();

    @Override
    public String saveAddress(Address address, Long countryId) {
        repository.saveAddress(address,countryId);
        return "Successfully saved!";
    }

    @Override
    public String saveAllAddress(List<Address> addresses, Long countryId) {
        repository.saveAllAddress(addresses,countryId);
        return "Successfully saved!";
    }

    @Override
    public List<Address> getAllAddresses() {
        return repository.getAllAddresses();
    }

    @Override
    public Optional<Address> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Address> deleteById(Long id) {
        return repository.findById(id);
    }

    @Override
    public String deleteAllAddress() {
        repository.deleteAllAddress();
        return "Successfully deleted!";
    }

    @Override
    public Optional<Address> updateAddress(Long id, Address newAddress) {
        return repository.updateAddress(id,newAddress);
    }
}
