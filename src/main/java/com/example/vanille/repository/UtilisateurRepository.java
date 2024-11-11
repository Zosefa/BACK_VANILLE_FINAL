package com.example.vanille.repository;

import com.example.vanille.model.Personne;
import com.example.vanille.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur , Integer> {
    @Query(value = """
    select * from utilisateur where id_personne=:id_personne
    """,nativeQuery = true)
    Optional<Utilisateur> select_utilisateur_by_personne(@Param("id_personne") int id_personne);

    @Query(value = """
    select * from utilisateur where id_utilisateur =:id
""",nativeQuery = true)
    Utilisateur getById(@Param("id") int id);

    @Query(value = """
    update utilisateur set pwsd =:psw where id_utilisateur =:id
""",nativeQuery = true)
    Utilisateur update_ById(@Param("id") int id,@Param("psw") String psw);
}
