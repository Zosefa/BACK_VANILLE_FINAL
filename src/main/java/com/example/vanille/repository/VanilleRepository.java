package com.example.vanille.repository;

import com.example.vanille.model.Utilisateur;
import com.example.vanille.model.Vanille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VanilleRepository extends JpaRepository<Vanille , Integer> {
    @Query(value = """
    SELECT *
    FROM vanille v
    LEFT JOIN disponible d ON v.id_vanille = d.idvanille
    WHERE d.idvanille IS NULL
""",nativeQuery = true)
    List<Vanille> select_Vanille();

    @Query(value = "SELECT COUNT(*) FROM vanille", nativeQuery = true)
    long countVanille();
}
