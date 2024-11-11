package com.example.vanille.model;

import jakarta.persistence.*;

@Entity
@Table(name = "type_divers")
public class Type_divers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_divers", nullable = false)
    private Integer id_type_divers;

    @Column(name = "nom")
    private String nom;

    public Integer getId_type_divers() {
        return id_type_divers;
    }

    public void setId_type_divers(Integer id_type_divers) {
        this.id_type_divers = id_type_divers;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
