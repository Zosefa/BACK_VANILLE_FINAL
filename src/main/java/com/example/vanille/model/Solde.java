package com.example.vanille.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "solde")
public class Solde {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solde", nullable = false)
    private Integer id_solde;

    @Column(name = "remise")
    private double remise;

    @Column(name = "debut")
    private Date debut;

    @Column(name = "fin")
    private Date fin;

    @Column(name = "affichage")
    private boolean affichage;

    @ManyToOne
    @JoinColumn(name = "id_disponible")
    private Disponible id_disponible;

    public Integer getId_solde() {
        return id_solde;
    }

    public void setId_solde(Integer id_solde) {
        this.id_solde = id_solde;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
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

    public Disponible getId_disponible() {
        return id_disponible;
    }

    public void setId_disponible(Disponible id_disponible) {
        this.id_disponible = id_disponible;
    }
}
