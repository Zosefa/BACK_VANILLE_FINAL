package com.example.vanille.controller;

import com.example.vanille.model.Disponible;
import com.example.vanille.model.Solde;
import com.example.vanille.model.Type;
import com.example.vanille.service.SoldeService;
import com.example.vanille.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/Type")
public class TypeControlle {
    @Autowired
    TypeService typeService;

    @PostMapping("/insertion_Type")
    public ResponseEntity<HashMap> insertion_Type(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String nom = data.get("nom");
        Type t = new Type();
        t.setNom(nom);
        try {
            Type type = this.typeService.enregistrerType(t);
            result.put("data",type);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/selectAll_Type")
    public ResponseEntity<HashMap> selectAll_Type() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<Type> types = this.typeService.selectAll_Type();
            result.put("data",types);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @PostMapping("/update_Type")
    public ResponseEntity<HashMap> update_Type(@RequestBody HashMap<String, String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        int id = Integer.parseInt(data.get("id"));
        String nom = data.get("nom");

        try {
            Type existingType = this.typeService.findById(id);

            if (existingType == null) {
                result.put("Erreur", "Type non trouvé avec l'ID: " + id);
                return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }

            existingType.setNom(nom);

            Type updatedType = this.typeService.enregistrerType(existingType);

            result.put("data", updatedType);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("Erreur", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
