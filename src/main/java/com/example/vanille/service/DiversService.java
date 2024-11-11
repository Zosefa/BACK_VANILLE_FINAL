package com.example.vanille.service;


import com.example.vanille.model.Disponible;
import com.example.vanille.model.Divers;
import com.example.vanille.repository.DisponibleRepository;
import com.example.vanille.repository.DiversRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiversService {
    @Autowired
    private DiversRepository diversRepository;

    public Divers enregistrerDivers(Divers divers) {
        return this.diversRepository.save(divers);
    }
    public Optional<Divers> select_Divers_By_id(int id) {
        return this.diversRepository.findById(id);
    }
    public List<Divers> selectAll_Divers() {
        return this.diversRepository.findAll();
    }

    public long getTotalDivers() {
        return diversRepository.countDivers();
    }
}
