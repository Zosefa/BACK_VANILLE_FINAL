package com.example.vanille.repository;

import com.example.vanille.model.Disponible;
import com.example.vanille.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisponibleRepository extends JpaRepository<Disponible , Integer> {
    @Query(value = """
    select * from disponible where (NOW() BETWEEN debut AND fin)
    """,nativeQuery = true)
    List<Disponible> selectDisponible();
}
