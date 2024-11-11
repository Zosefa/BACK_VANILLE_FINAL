package com.example.vanille.repository;

import com.example.vanille.model.Type;
import com.example.vanille.model.Type_divers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Type_diversRepository extends JpaRepository<Type_divers, Integer> {
}
