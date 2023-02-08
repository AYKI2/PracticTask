package org.example.repository;

import org.example.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {
    void saveAddress(Address address, Long countryId);
    void saveAllAddress(List<Address> addresses,Long countryId);
    List<Address> getAllAddresses();
    Optional<Address> findById(Long id);
    Optional<Address> deleteById(Long id);
    void deleteAllAddress();
    Optional<Address> updateAddress(Long id, Address newAddress);

}
