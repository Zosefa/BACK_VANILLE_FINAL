package com.example.vanille.controller;

import com.example.vanille.model.Disponible;
import com.example.vanille.model.Vanilla;
import com.example.vanille.model.Vanille;
import com.example.vanille.service.DisponibleService;
import com.example.vanille.service.VanillaService;
import com.example.vanille.service.VanilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Vanilla")
public class VanillaController {
    @Autowired
    VanillaService vanilleService;

    @PostMapping("/insertion_Vanilla")
    public ResponseEntity<HashMap> insertion_Vanilla(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String nom = data.get("nom");
        String email = data.get("email");
        String tel = data.get("tel");
        String dirigeant = data.get("dirigeant");
        String dascription = data.get("dascription");
        Vanilla v = new Vanilla();
        v.setNom(nom);
        v.setEmail(email);
        v.setTel(tel);
        v.setDirigeant(dirigeant);
        v.setDascription(dascription);
        try {
            Vanilla vanille = this.vanilleService.enregistrerVanilla(v);
            result.put("data",vanille);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/selectAll_Vanilla")
    public ResponseEntity<HashMap> selectAll_Vanilla() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<Vanilla> vanillas = this.vanilleService.selectAll_Vanilla();
            result.put("data",vanillas);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @PostMapping("/update_Vanilla")
    public ResponseEntity<HashMap<String, Object>> update_Vanilla(@RequestBody HashMap<String, String> data) {
        HashMap<String, Object> result = new HashMap<>();
        String id = data.get("id");
        String nom = data.get("nom");
        String email = data.get("email");
        String tel = data.get("tel");
        String dirigeant = data.get("dirigeant");
        String dascription = data.get("dascription");

        // Vérifiez si l'ID est fourni
        if (id == null || id.isEmpty()) {
            result.put("Erreur", "L'ID de l'entreprise est requis.");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        try {
            // Récupérez l'objet Vanilla existant par son ID
            Vanilla existingVanilla = vanilleService.findById(Integer.parseInt(id)); // Assurez-vous d'avoir une méthode pour récupérer par ID

            if (existingVanilla == null) {
                result.put("Erreur", "Aucune entreprise trouvée avec cet ID.");
                return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }

            // Mettez à jour les champs de l'objet existant
            existingVanilla.setNom(nom);
            existingVanilla.setEmail(email);
            existingVanilla.setTel(tel);
            existingVanilla.setDirigeant(dirigeant);
            existingVanilla.setDascription(dascription);

            // Enregistrez les modifications
            Vanilla updatedVanilla = vanilleService.enregistrerVanilla(existingVanilla);

            result.put("data", updatedVanilla);
            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (NumberFormatException e) {
            result.put("Erreur", "L'ID doit être un nombre valide.");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            result.put("Erreur", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
