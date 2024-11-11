package com.example.vanille.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vanille")
public class Vanille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vanille", nullable = false)
    private Integer id_vanille;

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "id_type")
    private Type id_type;

    public Integer getId_vanille() {
        return id_vanille;
    }

    public void setId_vanille(Integer id_vanille) {
        this.id_vanille = id_vanille;
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

    public Type getId_type() {
        return id_type;
    }

    public void setId_type(Type id_type) {
        this.id_type = id_type;
    }
}
