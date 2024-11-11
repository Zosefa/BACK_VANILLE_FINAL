package com.example.vanille.service;

import com.example.vanille.model.Divers;
import com.example.vanille.model.Personne;
import com.example.vanille.repository.DiversRepository;
import com.example.vanille.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneService {
    @Autowired
    private PersonneRepository personneRepository;

    public Personne enregistrerDivers(Personne personne) {
        return this.personneRepository.save(personne);
    }
    public Optional<Personne> select_Personne_By_id(int id) {
        return this.personneRepository.findById(id);
    }
    public List<Personne> selectAll_Personne() {
        return this.personneRepository.findAll();
    }

    public List<Personne> select_Last_Personne() {
        return this.personneRepository.select_max_id();
    }
    public Optional<Personne> select_personne_by_tel(String tel) {
        return this.personneRepository.select_personne_by_tel(tel);
    }

    public Personne updatePersonne(Integer id, Personne updatedPersonne) {
        Optional<Personne> existingPersonne = personneRepository.findById(id);

        if (existingPersonne.isPresent()) {
            Personne personne = existingPersonne.get();
            personne.setNom(updatedPersonne.getNom());
            personne.setTel(updatedPersonne.getTel());
            return personneRepository.save(personne);
        } else {
            throw new RuntimeException("Personne non trouvée avec l'id : " + id);
        }
    }
}
