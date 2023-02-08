package org.example.service;

import org.example.model.Programmer;
import org.example.repository.ProgrammerRepository;
import org.example.repository.ProgrammerRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class ProgrammerServiceImpl implements ProgrammerService {
    private ProgrammerRepository repository = new ProgrammerRepositoryImpl();
    @Override
    public String saveProgrammer(Programmer programmer, Long addressId) {
        repository.saveProgrammer(programmer, addressId);
        return "Successfully saved!";
    }

    @Override
    public List<Programmer> getAllProgrammer() {
        return repository.getAllProgrammer();
    }

    @Override
    public Optional<Programmer> findById(Long id) {
        boolean isTrue = false;
        for (Programmer prog:getAllProgrammer()) {
            if(prog.getId().equals(id)){
                isTrue = true;
            }
        }
        if (isTrue) {
            return repository.findById(id);
        }else {
            System.out.println("There is no programmer with this id!");
            return null;
        }
    }

    @Override
    public Optional<Programmer> deleteById(Long id) {
        if(findById(id).isPresent()){
            return repository.deleteById(id);
        }else {
            System.out.println("There is no programmer with this id!");
            return null;
        }
    }

    @Override
    public String deleteAllProgrammer() {
        repository.deleteAllProgrammer();
        return "Successfully cleaned!";
    }

    @Override
    public Optional<Programmer> updateProgrammer(Long id, Programmer newProgrammer) {
        if(findById(id).isPresent()){
            return repository.updateProgrammer(id,newProgrammer);
        }else {
            System.out.println("There is no programmer with this id!");
            return null;
        }
    }

    @Override
    public List<Programmer> findProgrammersByCountry(String name) {
        return repository.findProgrammersByCountry(name);
    }

    @Override
    public String getYoungProgrammer() {
        return repository.getYoungProgrammer();
    }

    @Override
    public String getOldProgrammer() {
        return repository.getOldProgrammer();
    }
}
