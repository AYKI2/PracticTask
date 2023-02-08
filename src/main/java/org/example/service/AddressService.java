package org.example.service;

import org.example.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    String saveAddress(Address address, Long countryId);
    String saveAllAddress(List<Address> addresses, Long countryId);
    List<Address> getAllAddresses();
    Optional<Address> findById(Long id);
    Optional<Address> deleteById(Long id);
    String deleteAllAddress();
    Optional<Address> updateAddress(Long id, Address newAddress);
}
