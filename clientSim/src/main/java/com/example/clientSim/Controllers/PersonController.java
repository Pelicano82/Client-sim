package com.example.clientSim.Controllers;

import com.example.clientSim.Service.Personservice;
import com.example.clientSim.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private Personservice personservice;

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person savedPerson = personservice.savePerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        Optional<Person> person = personservice.getPersonById(id);
        return person.map(ResponseEntity::ok)
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personservice.getAllPersons();
        return ResponseEntity.ok(persons);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
        Optional<Person> person = personservice.getPersonById(id);
        if (person.isPresent()) {
            Person existingPerson = person.get();
            existingPerson.setAddresses(personDetails.getAddresses());
            existingPerson.setTownCity(personDetails.getTownCity());
            existingPerson.setPostcode(personDetails.getPostcode());
            existingPerson.setCountry(personDetails.getCountry());
            existingPerson.setEmail(personDetails.getEmail());
            existingPerson.setCard(personDetails.getCard());
            Person updatedPerson = personservice.savePerson(existingPerson);
            return ResponseEntity.ok(updatedPerson);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        if (personservice.personExists(id)) {
            personservice.deletePerson(id);
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

