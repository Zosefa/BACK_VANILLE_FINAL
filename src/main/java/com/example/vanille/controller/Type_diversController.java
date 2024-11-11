package com.example.vanille.controller;

import com.example.vanille.model.Type;
import com.example.vanille.model.Type_divers;
import com.example.vanille.service.TypeService;
import com.example.vanille.service.Type_diversService;
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
@RequestMapping("/Type_divers")
public class Type_diversController {
    @Autowired
    Type_diversService type_diversService;

    @PostMapping("/insertion_Type_divers")
    public ResponseEntity<HashMap> insertion_Type_divers(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String nom = data.get("nom");
        Type_divers t = new Type_divers();
        t.setNom(nom);
        try {
            Type_divers type_divers = this.type_diversService.enregistrerType_divers(t);
            result.put("data",type_divers);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/selectAll_Type_divers")
    public ResponseEntity<HashMap> selectAll_Type_divers() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<Type_divers> type_divers = this.type_diversService.selectAll_Type_divers();
            result.put("data",type_divers);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @PostMapping("/update_Type_divers")
    public ResponseEntity<HashMap> update_Type_divers(@RequestBody HashMap<String, String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();

        int id = Integer.parseInt(data.get("id"));
        String nom = data.get("nom");

        try {
            Type_divers existingTypeDivers = this.type_diversService.findById(id);

            if (existingTypeDivers == null) {
                result.put("Erreur", "Type divers non trouvé avec l'ID: " + id);
                return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }

            existingTypeDivers.setNom(nom);

            Type_divers updatedTypeDivers = this.type_diversService.enregistrerType_divers(existingTypeDivers);

            result.put("data", updatedTypeDivers);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("Erreur", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
