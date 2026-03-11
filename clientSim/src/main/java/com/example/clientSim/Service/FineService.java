package com.example.clientSim.Service;

import com.example.clientSim.Repos.FineRepo;
import com.example.clientSim.entity.Fine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FineService {

    @Autowired
    private FineRepo fineRepo;

    public Fine saveFine(Fine fine) {
        return fineRepo.save(fine);
    }

    public Optional<Fine> getFineById(Long id) {
        return fineRepo.findById(id);
    }

    public List<Fine> getFinesByLicenceId(Long licenceId) {
        return fineRepo.findByLicenceId(licenceId);
    }

    public List<Fine> getAllFines() {
        return fineRepo.findAll();
    }

    public void deleteFine(Long id) {
        fineRepo.deleteById(id);
    }

    public boolean fineExists(Long id) {
        return fineRepo.existsById(id);
    }
}
