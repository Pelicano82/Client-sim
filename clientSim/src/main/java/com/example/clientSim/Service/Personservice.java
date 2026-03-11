package com.example.clientSim.Service;

import com.example.clientSim.Repos.Personrepo;
import com.example.clientSim.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Personservice {

    @Autowired
    private Personrepo personrepo;

    public Person savePerson(Person person) {
        return personrepo.save(person);
    }

    public Optional<Person> getPersonById(Long id) {
        return personrepo.findById(id);
    }

    public List<Person> getAllPersons() {
        return personrepo.findAll();
    }

    public void deletePerson(Long id) {
        personrepo.deleteById(id);
    }

    public boolean personExists(Long id) {
        return personrepo.existsById(id);
    }
}
