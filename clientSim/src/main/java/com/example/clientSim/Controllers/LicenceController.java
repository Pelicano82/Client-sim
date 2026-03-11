package com.example.clientSim.Controllers;

import com.example.clientSim.Service.LicenceService;
import com.example.clientSim.entity.Licence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/licences")
public class LicenceController {

    @Autowired
    private LicenceService licenceService;

    @PostMapping
    public ResponseEntity<Licence> createLicence(@RequestBody Licence licence) {
        Licence savedLicence = licenceService.saveLicence(licence);
        return new ResponseEntity<>(savedLicence, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Licence> getLicence(@PathVariable Long id) {
        Optional<Licence> licence = licenceService.getLicenceById(id);
        return licence.map(ResponseEntity::ok)
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/number/{licenceNumber}")
    public ResponseEntity<Licence> getLicenceByNumber(@PathVariable String licenceNumber) {
        Optional<Licence> licence = licenceService.getLicenceByNumber(licenceNumber);
        return licence.map(ResponseEntity::ok)
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Licence>> getAllLicences() {
        List<Licence> licences = licenceService.getAllLicences();
        return ResponseEntity.ok(licences);
    }

    @GetMapping("/person/{personId}")
    public ResponseEntity<List<Licence>> getLicencesByPerson(@PathVariable Long personId) {
        List<Licence> licences = licenceService.getLicencesByPersonId(personId);
        return ResponseEntity.ok(licences);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Licence> updateLicence(@PathVariable Long id, @RequestBody Licence licenceDetails) {
        Optional<Licence> licence = licenceService.getLicenceById(id);
        if (licence.isPresent()) {
            Licence existingLicence = licence.get();
            existingLicence.setLicenceNumber(licenceDetails.getLicenceNumber());
            existingLicence.setFee(licenceDetails.getFee());
            existingLicence.setValidUntil(licenceDetails.getValidUntil());
            existingLicence.setStatus(licenceDetails.getStatus());
            existingLicence.setRenewed(licenceDetails.getRenewed());
            Licence updatedLicence = licenceService.saveLicence(existingLicence);
            return ResponseEntity.ok(updatedLicence);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLicence(@PathVariable Long id) {
        if (licenceService.licenceExists(id)) {
            licenceService.deleteLicence(id);
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
