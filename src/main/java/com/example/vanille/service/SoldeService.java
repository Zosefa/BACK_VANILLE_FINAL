package com.example.vanille.service;

import com.example.vanille.model.Personne;
import com.example.vanille.model.Solde;
import com.example.vanille.repository.PersonneRepository;
import com.example.vanille.repository.SoldeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoldeService {
    @Autowired
    private SoldeRepository soldeRepository;

    public Solde enregistrerSolde(Solde solde) {
        return this.soldeRepository.save(solde);
    }
    public Optional<Solde> select_Solde_By_id(int id) {
        return this.soldeRepository.findById(id);
    }
    public List<Solde> selectAll_Solde() {
        return this.soldeRepository.findAll();
    }

    public List<Solde> select_Solde() {
        return this.soldeRepository.select_Solde();
    }
}
