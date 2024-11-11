package com.example.vanille.controller;


import com.example.vanille.model.Disponible;
import com.example.vanille.model.Vanille;
import com.example.vanille.repository.VanilleRepository;
import com.example.vanille.service.DisponibleService;
import com.example.vanille.service.VanilleService;
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
@RequestMapping("/Disponible")
public class DisponibleController {
    @Autowired
    DisponibleService disponibleService;

    @Autowired
    VanilleService vanilleService;

    @PostMapping("/insertion_Disponible")
    public ResponseEntity<HashMap> insertion_Disponible(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String idvanille = data.get("idvanille");
        String debut = data.get("debut");
        String fin = data.get("fin");
        String affichage = data.get("affichage");
        Optional<Vanille> vanille = this.vanilleService.select_Vanille_By_id(Integer.parseInt(idvanille));
        Disponible d = new Disponible();
        d.setIdvanille(vanille.get());
        d.setDebut(Date.valueOf(debut));
        d.setFin(Date.valueOf(fin));
        d.setAffichage(Boolean.parseBoolean(affichage));
        try {
            Disponible disponible = this.disponibleService.enregistrerDisponible(d);
            result.put("data",disponible);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @PutMapping("/modifier_Disponible/{id}")
    public ResponseEntity<HashMap> modifier_Disponible(@PathVariable int id, @RequestBody HashMap<String, String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String debut = data.get("debut");
        String fin = data.get("fin");
        String affichage = data.get("affichage");

        try {
            Optional<Disponible> disponibleOpt = this.disponibleService.select_Disponible_By_id(id);

            if (disponibleOpt.isPresent()) {
                Disponible disponible = disponibleOpt.get();
                if (debut != null) disponible.setDebut(Date.valueOf(debut));
                if (fin != null) disponible.setFin(Date.valueOf(fin));
                if (affichage != null) disponible.setAffichage(Boolean.parseBoolean(affichage));

                Disponible updatedDisponible = this.disponibleService.enregistrerDisponible(disponible);
                result.put("data", updatedDisponible);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.put("Erreur", "Disponible introuvable avec l'ID spécifié");
                return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            result.put("Erreur", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/selectAll_Disponible")
    public ResponseEntity<HashMap> selectAll_Disponible() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<Disponible> disponibles = this.disponibleService.selectAll_Disponible();
            result.put("data",disponibles);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @GetMapping("/select_Disponible")
    public ResponseEntity<HashMap> select_Disponible() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<Disponible> disponibles = this.disponibleService.select_Disponible();
            result.put("data",disponibles);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
