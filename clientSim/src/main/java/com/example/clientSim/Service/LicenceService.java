package com.example.clientSim.Service;

import com.example.clientSim.Repos.LicenceRepo;
import com.example.clientSim.entity.Licence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LicenceService {

    @Autowired
    private LicenceRepo licenceRepo;

    public Licence saveLicence(Licence licence) {
        return licenceRepo.save(licence);
    }

    public Optional<Licence> getLicenceById(Long id) {
        return licenceRepo.findById(id);
    }

    public Optional<Licence> getLicenceByNumber(String licenceNumber) {
        return licenceRepo.findByLicenceNumber(licenceNumber);
    }

    public List<Licence> getLicencesByPersonId(Long personId) {
        return licenceRepo.findByPersonId(personId);
    }

    public List<Licence> getAllLicences() {
        return licenceRepo.findAll();
    }

    public void deleteLicence(Long id) {
        licenceRepo.deleteById(id);
    }

    public boolean licenceExists(Long id) {
        return licenceRepo.existsById(id);
    }
}
