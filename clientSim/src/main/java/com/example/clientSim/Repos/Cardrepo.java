package com.example.clientSim.Repos;

import com.example.clientSim.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cardrepo extends JpaRepository<Card, String> {
}
