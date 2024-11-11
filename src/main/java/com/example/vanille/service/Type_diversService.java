package com.example.vanille.service;

import com.example.vanille.model.Solde;
import com.example.vanille.model.Type_divers;
import com.example.vanille.model.Vanilla;
import com.example.vanille.repository.SoldeRepository;
import com.example.vanille.repository.Type_diversRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Type_diversService {
    @Autowired
    private Type_diversRepository type_diversRepository;

    public Type_divers enregistrerType_divers(Type_divers type_divers) {
        return this.type_diversRepository.save(type_divers);
    }
    public Optional<Type_divers> select_Type_divers_By_id(int id) {
        return this.type_diversRepository.findById(id);
    }
    public List<Type_divers> selectAll_Type_divers() {
        return this.type_diversRepository.findAll();
    }

    public Type_divers findById(int id) {
        Optional<Type_divers> optionalTypeDivers = type_diversRepository.findById(id);
        return optionalTypeDivers.orElse(null);
    }
}
