package com.example.vanille.controller;

import com.example.vanille.configuration.JWTManager;
import com.example.vanille.model.Personne;
import com.example.vanille.model.Utilisateur;
import com.example.vanille.service.PersonneService;
import com.example.vanille.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/Utilisateur")
public class UtilisateurController {
    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    PersonneService personneService;

    @Autowired
    JWTManager jwtManager;

    @PostMapping("/insertion_Utilisateur")
    public ResponseEntity<HashMap> insertion_Utilisateur(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String id_personne = data.get("id_personne");
        String pwsd = "000000";
        Optional<Personne> personne = this.personneService.select_Personne_By_id(Integer.parseInt(id_personne));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(pwsd);
        Utilisateur u = new Utilisateur();
        u.setId_personne(personne.get());
        u.setPwsd(hashedPassword);
        try {
            Utilisateur utilisateur = this.utilisateurService.enregistrerUtilisateur(u);
            result.put("data",utilisateur);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @PostMapping("/checking")
    public ResponseEntity<HashMap> checking(@RequestBody Map<String, String> credentials) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String tel = credentials.get("tel");
        String pswd = credentials.get("pswd");
        try {
            Optional<Personne> personne = this.personneService.select_personne_by_tel(tel);
            if (personne != null) {
                Optional<Utilisateur> utilisateur = this.utilisateurService.select_utilisateur_by_personne(personne.get().getId_personne());
                if (utilisateur != null){
                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    if (passwordEncoder.matches(pswd, utilisateur.get().getPwsd())) {
                        result.put("data", jwtManager.generateToken(utilisateur.get()));
                        return new ResponseEntity<>(result, HttpStatus.OK);
                    } else {
                        result.put("Erreur", "Mot de passe incorrect.");
                        return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
                    }
                }
            }
            else {
                result.put("Erreur", "Numero de telephone incorrect");
                return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/selectAll_Utilisateur")
    public ResponseEntity<HashMap> selectAll_Type_divers() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<Utilisateur> utilisateur = this.utilisateurService.selectAll_Utilisateur();
            result.put("data",utilisateur);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @PostMapping("/update_Utilisateur")
    public ResponseEntity<HashMap> Update_Utilisateur(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String pwsd = data.get("psw");
        String nom = data.get("nom");
        String tel = data.get("tel");
        String token = data.get("token");
        int id = Integer.parseInt(jwtManager.getClaim(token, "id"));
        Utilisateur utilisateur = this.utilisateurService.getById(id);
        Personne personne = utilisateur.getId_personne();
        personne.setTel(tel);
        personne.setNom(nom);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(pwsd);
        try {
            this.personneService.updatePersonne(utilisateur.getId_personne().getId_personne(),personne);
            this.utilisateurService.Update_ById(id,hashedPassword);
            result.put("data","mot de passe changer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @PostMapping("/select_Utilisateur")
    public ResponseEntity<HashMap> select_Utilisateur(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String token = data.get("token");
        int id = Integer.parseInt(jwtManager.getClaim(token, "id"));
        try {
            Utilisateur utilisateur = this.utilisateurService.getById(id);
            result.put("data",utilisateur);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
