package org.example.service;

import org.example.model.Programmer;

import java.util.List;
import java.util.Optional;

public interface ProgrammerService {
    String saveProgrammer(Programmer programmer, Long addressId);
    List<Programmer> getAllProgrammer();
    Optional<Programmer> findById(Long id);
    Optional<Programmer> deleteById(Long id);
    String deleteAllProgrammer();
    Optional<Programmer> updateProgrammer(Long id, Programmer newProgrammer);
    List<Programmer> findProgrammersByCountry(String name);
    String getYoungProgrammer();
    String getOldProgrammer();
}
