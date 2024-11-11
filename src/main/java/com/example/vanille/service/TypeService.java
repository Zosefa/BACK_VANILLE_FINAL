package com.example.vanille.service;

import com.example.vanille.model.Solde;
import com.example.vanille.model.Type;
import com.example.vanille.repository.SoldeRepository;
import com.example.vanille.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    public Type enregistrerType(Type type) {
        return this.typeRepository.save(type);
    }
    public Optional<Type> select_Type_By_id(int id) {
        return this.typeRepository.findById(id);
    }
    public List<Type> selectAll_Type() {
        return this.typeRepository.findAll();
    }

    public Type findById(int id) {
        Optional<Type> optionalType = typeRepository.findById(id);
        return optionalType.orElse(null);
    }
}
