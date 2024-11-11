package com.example.vanille.model;

import jakarta.persistence.*;

@Entity
@Table(name = "divers")
public class Divers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_divers", nullable = false)
    private Integer id_divers;

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "id_type_divers")
    private Type_divers id_type_divers;

    public Integer getId_divers() {
        return id_divers;
    }

    public void setId_divers(Integer id_divers) {
        this.id_divers = id_divers;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Type_divers getId_type_divers() {
        return id_type_divers;
    }

    public void setId_type_divers(Type_divers id_type_divers) {
        this.id_type_divers = id_type_divers;
    }
}
