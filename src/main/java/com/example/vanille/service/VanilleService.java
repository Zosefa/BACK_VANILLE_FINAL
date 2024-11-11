package com.example.vanille.service;

import com.example.vanille.model.Vanille;
import com.example.vanille.repository.VanilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VanilleService {
    @Autowired
    private VanilleRepository vanilleRepository;

    public Vanille enregistrerVanille(Vanille vanille) {
        return this.vanilleRepository.save(vanille);
    }
    public Optional<Vanille> select_Vanille_By_id(int id) {
        return this.vanilleRepository.findById(id);
    }
    public List<Vanille> selectAll_Vanille() {
        return this.vanilleRepository.findAll();
    }
    public List<Vanille> select_Vanille() {
        return this.vanilleRepository.select_Vanille();
    }

    public long getTotalVanille() {
        return vanilleRepository.countVanille();
    }
}
