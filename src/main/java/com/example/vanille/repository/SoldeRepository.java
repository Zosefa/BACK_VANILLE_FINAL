package com.example.vanille.repository;

import com.example.vanille.model.Disponible;
import com.example.vanille.model.Solde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoldeRepository extends JpaRepository<Solde , Integer> {

    @Query(value = """
    select * from solde where (NOW() BETWEEN debut AND fin) AND affichage = true 
    """,nativeQuery = true)
    List<Solde> select_Solde();
}
