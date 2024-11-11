package com.example.vanille.repository;

import com.example.vanille.model.Vanilla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VanillaRepository extends JpaRepository<Vanilla,Integer > {
}
