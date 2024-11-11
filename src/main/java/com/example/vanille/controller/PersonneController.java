package com.example.vanille.controller;

import com.example.vanille.model.Divers;
import com.example.vanille.model.Personne;
import com.example.vanille.model.Type_divers;
import com.example.vanille.service.DiversService;
import com.example.vanille.service.PersonneService;
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
import java.util.Optional;

@Controller
@RequestMapping("/Personne")
public class PersonneController {
    @Autowired
    PersonneService personneService;


    @PostMapping("/insertion_Personne")
    public ResponseEntity<HashMap> insertion_Personne(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String nom = data.get("nom");
        String tel = data.get("tel");
        Personne p = new Personne();
        p.setNom(nom);
        p.setTel(tel);
        try {
            Personne personne = this.personneService.enregistrerDivers(p);
            result.put("data",personne);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/selectAll_Personne")
    public ResponseEntity<HashMap> selectAll_Personne() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<Personne> personne = this.personneService.selectAll_Personne();
            result.put("data",personne);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @GetMapping("/select_Last_Personne")
    public ResponseEntity<HashMap> select_Last_Personne() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<Personne> personne = this.personneService.select_Last_Personne();
            result.put("data",personne);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
