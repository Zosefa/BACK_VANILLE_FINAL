package com.example.vanille.controller;

import com.example.vanille.model.Disponible;
import com.example.vanille.model.Divers;
import com.example.vanille.model.Type_divers;
import com.example.vanille.model.Vanille;
import com.example.vanille.service.DisponibleService;
import com.example.vanille.service.DiversService;
import com.example.vanille.service.Type_diversService;
import com.example.vanille.service.VanilleService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.*;

@Controller
@RequestMapping("/Divers")
public class DiversController {
    @Autowired
    DiversService diversService;

    @Autowired
    Type_diversService type_diversService;

    private final String UPLOAD_DIR ="Uploads";
    @PostMapping(value = "/insertion_Divers", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HashMap> insertion_Divers(
            @RequestPart("credentials") String credentialsJson,
            @RequestParam(value = "image",required = false) List<MultipartFile> images) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> credentials = objectMapper.readValue(credentialsJson, new TypeReference<Map<String, String>>() {});
        String nom = credentials.get("nom");
        String description = credentials.get("description");
        String id_type_divers = credentials.get("id_type_divers");
        Optional<Type_divers> type_divers = this.type_diversService.select_Type_divers_By_id(Integer.parseInt(id_type_divers));
        Divers d = new Divers();
        d.setNom(nom);
        d.setDescription(description);
        d.setId_type_divers(type_divers.get());
        try {
            List<String> imagesS = uploadFiles(images);
            String resultat = String.join(",", imagesS);
            d.setImage(resultat);
            Divers divers = this.diversService.enregistrerDivers(d);
            result.put("data",divers);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @PutMapping(value = "/update_Divers/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HashMap> update_Divers(
            @PathVariable("id") int id,
            @RequestPart("credentials") String credentialsJson,
            @RequestParam(value = "image", required = false) List<MultipartFile> images) throws Exception {

        HashMap<String, Object> result = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> credentials = objectMapper.readValue(credentialsJson, new TypeReference<Map<String, String>>() {});

        String nom = credentials.get("nom");
        String description = credentials.get("description");
        String id_type_divers = credentials.get("id_type_divers");

        try {
            // Rechercher le Divers existant par son ID
            Optional<Divers> diversOptional = this.diversService.select_Divers_By_id(id);
            if (!diversOptional.isPresent()) {
                result.put("Erreur", "Divers non trouvé avec l'ID : " + id);
                return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }

            Divers divers = diversOptional.get();

            // Mettre à jour les champs
            divers.setNom(nom);
            divers.setDescription(description);

            // Récupérer et définir le type_divers
            Optional<Type_divers> type_divers = this.type_diversService.select_Type_divers_By_id(Integer.parseInt(id_type_divers));
            if (!type_divers.isPresent()) {
                result.put("Erreur", "Type divers non trouvé avec l'ID : " + id_type_divers);
                return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }
            divers.setId_type_divers(type_divers.get());

            // Si de nouvelles images sont fournies, les télécharger et mettre à jour
            if (images != null && !images.isEmpty()) {
                List<String> imagesS = uploadFiles(images);
                String resultat = String.join(",", imagesS);
                divers.setImage(resultat);
            }

            // Sauvegarder les modifications
            Divers updatedDivers = this.diversService.enregistrerDivers(divers);
            result.put("data", updatedDivers);
            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            result.put("Erreur", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<String> uploadFiles( List<MultipartFile> files) throws IOException {
        List<String> fileNames = new ArrayList<>();

        // Crée le répertoire s'il n'existe pas déjà
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        for (MultipartFile file : files) {
            // Obtenez le nom de fichier original
            String fileName = file.getOriginalFilename();

            // Crée le chemin complet pour le fichier
            Path filePath = Paths.get(UPLOAD_DIR, fileName);

            // Enregistrer le fichier dans le dossier
            file.transferTo(filePath);

            // Ajoutez le chemin relatif
            fileNames.add("/Uploads/"+fileName);
        }
        return fileNames;
    }
    @GetMapping("/selectAll_Divers")
    public ResponseEntity<HashMap> selectAll_Disponible() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<Divers> divers = this.diversService.selectAll_Divers();
            result.put("data",divers);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<HashMap<String, Object>> getTotalDivers() {
        HashMap<String, Object> result = new HashMap<>();
        try {
            long total = diversService.getTotalDivers();
            result.put("total", total);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("Erreur", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
