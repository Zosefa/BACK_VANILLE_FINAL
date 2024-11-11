package com.example.vanille.model;

import jakarta.persistence.*;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur", nullable = false)
    private Integer id_utilisateur;

    @Column(name = "pwsd")
    private String pwsd;

    @ManyToOne
    @JoinColumn(name = "id_personne")
    private Personne id_personne;

    public Integer getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(Integer id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getPwsd() {
        return pwsd;
    }

    public void setPwsd(String pwsd) {
        this.pwsd = pwsd;
    }

    public Personne getId_personne() {
        return id_personne;
    }

    public void setId_personne(Personne id_personne) {
        this.id_personne = id_personne;
    }
}
