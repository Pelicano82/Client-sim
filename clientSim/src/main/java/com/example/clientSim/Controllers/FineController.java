package com.example.clientSim.Controllers;

import com.example.clientSim.Service.FineService;
import com.example.clientSim.entity.Fine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fines")
public class FineController {

    @Autowired
    private FineService fineService;

    @PostMapping
    public ResponseEntity<Fine> createFine(@RequestBody Fine fine) {
        Fine savedFine = fineService.saveFine(fine);
        return new ResponseEntity<>(savedFine, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fine> getFine(@PathVariable Long id) {
        Optional<Fine> fine = fineService.getFineById(id);
        return fine.map(ResponseEntity::ok)
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Fine>> getAllFines() {
        List<Fine> fines = fineService.getAllFines();
        return ResponseEntity.ok(fines);
    }

    @GetMapping("/licence/{licenceId}")
    public ResponseEntity<List<Fine>> getFinesByLicence(@PathVariable Long licenceId) {
        List<Fine> fines = fineService.getFinesByLicenceId(licenceId);
        return ResponseEntity.ok(fines);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fine> updateFine(@PathVariable Long id, @RequestBody Fine fineDetails) {
        Optional<Fine> fine = fineService.getFineById(id);
        if (fine.isPresent()) {
            Fine existingFine = fine.get();
            existingFine.setAmount(fineDetails.getAmount());
            existingFine.setReason(fineDetails.getReason());
            existingFine.setStatus(fineDetails.getStatus());
            Fine updatedFine = fineService.saveFine(existingFine);
            return ResponseEntity.ok(updatedFine);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFine(@PathVariable Long id) {
        if (fineService.fineExists(id)) {
            fineService.deleteFine(id);
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
