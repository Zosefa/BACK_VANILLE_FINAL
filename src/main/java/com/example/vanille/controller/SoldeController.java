package com.example.vanille.controller;


import com.example.vanille.model.Disponible;
import com.example.vanille.model.Personne;
import com.example.vanille.model.Solde;
import com.example.vanille.service.DisponibleService;
import com.example.vanille.service.PersonneService;
import com.example.vanille.service.SoldeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Solde")
public class SoldeController {
    @Autowired
    SoldeService soldeService;
    @Autowired
    DisponibleService disponibleService;

    @PostMapping("/insertion_Solde")
    public ResponseEntity<HashMap> insertion_Solde(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String id_disponible = data.get("id_disponible");
        String remise = data.get("remise");
        String debut = data.get("debut");
        String fin = data.get("fin");
        String affichage = data.get("affichage");
        Optional<Disponible> disponible = this.disponibleService.select_Disponible_By_id(Integer.parseInt(id_disponible));
        Solde s = new Solde();
        s.setId_disponible(disponible.get());
        s.setRemise(Double.parseDouble(remise));
        s.setDebut(Date.valueOf(debut));
        s.setFin(Date.valueOf(fin));
        s.setAffichage(Boolean.parseBoolean(affichage));
        try {
            Solde solde = this.soldeService.enregistrerSolde(s);
            result.put("data",solde);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/selectAll_Solde")
    public ResponseEntity<HashMap> selectAll_Personne() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<Solde> soldes = this.soldeService.selectAll_Solde();
            result.put("data",soldes);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @GetMapping("/select_Solde")
    public ResponseEntity<HashMap> select_Solde() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<Solde> soldes = this.soldeService.select_Solde();
            result.put("data",soldes);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @PutMapping("/modifier_Solde/{id}")
    public ResponseEntity<HashMap<String, Object>> updateSolde(@PathVariable("id") int id, @RequestBody HashMap<String, String> data) {
        HashMap<String, Object> result = new HashMap<>();
        try {
            Optional<Solde> existingSoldeOpt = soldeService.select_Solde_By_id(id);
            if (!existingSoldeOpt.isPresent()) {
                result.put("Erreur", "Solde non trouvé");
                return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }

            Solde existingSolde = existingSoldeOpt.get();
            String remise = data.get("remise");
            String debut = data.get("debut");
            String fin = data.get("fin");
            String affichage = data.get("affichage");

            if (remise != null) existingSolde.setRemise(Double.parseDouble(remise));
            if (debut != null) existingSolde.setDebut(Date.valueOf(debut));
            if (fin != null) existingSolde.setFin(Date.valueOf(fin));
            if (affichage != null) existingSolde.setAffichage(Boolean.parseBoolean(affichage));
            Solde updatedSolde = soldeService.enregistrerSolde(existingSolde);
            result.put("data", updatedSolde);
            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            result.put("Erreur", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
