package com.example.vanille.repository;

import com.example.vanille.model.Divers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DiversRepository extends JpaRepository<Divers, Integer> {
    @Query(value = "SELECT COUNT(*) FROM divers", nativeQuery = true)
    long countDivers();
}
