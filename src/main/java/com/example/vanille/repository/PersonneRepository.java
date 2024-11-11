package com.example.vanille.repository;

import com.example.vanille.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonneRepository extends JpaRepository<Personne , Integer> {
    @Query(value = """
    select * from personne where tel=:tel
    """,nativeQuery = true)
    Optional<Personne> select_personne_by_tel(@Param("tel") String tel);

    @Query(value = """
    select * from personne where id_personne = (select MAX(id_personne) from personne)
    """,nativeQuery = true)
    List<Personne> select_max_id();

}
