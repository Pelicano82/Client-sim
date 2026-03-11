package com.example.clientSim.Repos;

import com.example.clientSim.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Personrepo extends JpaRepository<Person, Long> {
}
