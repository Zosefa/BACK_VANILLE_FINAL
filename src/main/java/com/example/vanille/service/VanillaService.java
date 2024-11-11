package com.example.vanille.service;

import com.example.vanille.model.Vanilla;
import com.example.vanille.model.Vanille;
import com.example.vanille.repository.VanillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VanillaService {
    @Autowired
    private VanillaRepository vanillaRepository;

    public Vanilla enregistrerVanilla(Vanilla vanilla) {
        return this.vanillaRepository.save(vanilla);
    }
    public Optional<Vanilla> select_Vanilla_By_id(int id) {
        return this.vanillaRepository.findById(id);
    }
    public List<Vanilla> selectAll_Vanilla() {
        return this.vanillaRepository.findAll();
    }

    public Vanilla findById(int id) {
        return vanillaRepository.findById(id).orElse(null); // Utilisez Optional pour gérer la possibilité de non-existence
    }
}
