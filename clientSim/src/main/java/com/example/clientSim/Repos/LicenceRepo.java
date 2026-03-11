package com.example.clientSim.Repos;

import com.example.clientSim.entity.Licence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LicenceRepo extends JpaRepository<Licence, Long> {
    Optional<Licence> findByLicenceNumber(String licenceNumber);
    List<Licence> findByPersonId(Long personId);
}
