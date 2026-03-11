package com.example.clientSim.Repos;

import com.example.clientSim.entity.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FineRepo extends JpaRepository<Fine, Long> {
    List<Fine> findByLicenceId(Long licenceId);
}
