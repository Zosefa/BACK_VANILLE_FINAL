package com.example.vanille.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "disponible")
public class Disponible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disponible", nullable = false)
    private Integer id_disponible;

    @Column(name = "debut")
    private Date debut;

    @Column(name = "fin")
    private Date fin;

    @Column(name = "affichage")
    private boolean affichage;

    @ManyToOne
    @JoinColumn(name = "idvanille")
    private Vanille idvanille;

    public Integer getId_disponible() {
        return id_disponible;
    }

    public void setId_disponible(Integer id_disponible) {
        this.id_disponible = id_disponible;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public boolean isAffichage() {
        return affichage;
    }

    public void setAffichage(boolean affichage) {
        this.affichage = affichage;
    }

    public Vanille getIdvanille() {
        return idvanille;
    }

    public void setIdvanille(Vanille idvanille) {
        this.idvanille = idvanille;
    }
}
