package com.example.vanille.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vanilla")
public class Vanilla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vanilla", nullable = false)
    private Integer id_vanilla;

    @Column(name = "nom")
    private String nom;

    @Column(name = "email")
    private String email;

    @Column(name = "tel")
    private String tel;

    @Column(name = "dirigeant")
    private String dirigeant;

    @Column(name = "dascription")
    private String dascription;

    public Integer getId_vanilla() {
        return id_vanilla;
    }

    public void setId_vanilla(Integer id_vanilla) {
        this.id_vanilla = id_vanilla;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDirigeant() {
        return dirigeant;
    }

    public void setDirigeant(String dirigeant) {
        this.dirigeant = dirigeant;
    }

    public String getDascription() {
        return dascription;
    }

    public void setDascription(String dascription) {
        this.dascription = dascription;
    }
}
