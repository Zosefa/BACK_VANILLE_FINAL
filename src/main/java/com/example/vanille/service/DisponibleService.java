package com.example.vanille.service;

import com.example.vanille.model.Disponible;
import com.example.vanille.repository.DisponibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisponibleService {
    @Autowired
    private DisponibleRepository disponibleRepository;

    public Disponible enregistrerDisponible(Disponible disponible) {
        return this.disponibleRepository.save(disponible);
    }
    public Optional<Disponible> select_Disponible_By_id(int id) {
        return this.disponibleRepository.findById(id);
    }
    public List<Disponible> selectAll_Disponible() {
        return this.disponibleRepository.findAll();
    }

    public List<Disponible> select_Disponible() { return this.disponibleRepository.selectDisponible(); }
}
