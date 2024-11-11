package com.example.vanille.service;

import com.example.vanille.model.Type;
import com.example.vanille.model.Utilisateur;
import com.example.vanille.repository.TypeRepository;
import com.example.vanille.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur enregistrerUtilisateur(Utilisateur utilisateur) {
        return this.utilisateurRepository.save(utilisateur);
    }
    public Optional<Utilisateur> select_Utilisateur_By_id(int id) {
        return this.utilisateurRepository.findById(id);
    }
    public Utilisateur getById(int id) {
        return this.utilisateurRepository.getById(id);
    }

    public Utilisateur Update_ById(int id,String psw) {
        return this.utilisateurRepository.update_ById(id,psw);
    }
    public List<Utilisateur> selectAll_Utilisateur() {
        return this.utilisateurRepository.findAll();
    }
    public Optional<Utilisateur> select_utilisateur_by_personne(int personne) {
        return this.utilisateurRepository.select_utilisateur_by_personne(personne);
    }
}
