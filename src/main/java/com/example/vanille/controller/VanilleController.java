package com.example.vanille.controller;


import com.example.vanille.model.Disponible;
import com.example.vanille.model.Type;
import com.example.vanille.model.Vanille;
import com.example.vanille.service.DisponibleService;
import com.example.vanille.service.TypeService;
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
import java.util.*;

@Controller
@RequestMapping("/Vanille")
public class VanilleController {
    @Autowired
    VanilleService vanilleService;

    @Autowired
    TypeService typeService;

    private final String UPLOAD_DIR ="Uploads";

    @PostMapping(value = "/insertion_Vanille", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HashMap> insertion_Vanille(
            @RequestPart("credentials") String credentialsJson,
            @RequestParam(value = "image",required = false) List<MultipartFile> images) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> credentials = objectMapper.readValue(credentialsJson, new TypeReference<Map<String, String>>() {});

        String nom = credentials.get("nom");
        String description = credentials.get("description");
        String id_type = credentials.get("id_type");
        Optional<Type> type = this.typeService.select_Type_By_id(Integer.parseInt(id_type));
        Vanille v = new Vanille();
        v.setNom(nom);
        v.setDescription(description);
        v.setId_type(type.get());
        try {
            List<String> imagesS = uploadFiles(images);
            String resultat = String.join(",", imagesS);
            v.setImage(resultat);
            Vanille vanille = this.vanilleService.enregistrerVanille(v);;
            result.put("data",vanille);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @PutMapping(value = "/update_Vanille/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateVanille(
            @PathVariable("id") Integer id,
            @RequestPart("credentials") String credentialsJson,
            @RequestParam(value = "image", required = false) List<MultipartFile> images) throws Exception {

        HashMap<String, Object> result = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> credentials = objectMapper.readValue(credentialsJson, new TypeReference<Map<String, String>>() {});

        try {
            Optional<Vanille> vanilleOpt = this.vanilleService.select_Vanille_By_id(id);
            if (vanilleOpt.isEmpty()) {
                result.put("Erreur", "Vanille avec l'ID spécifié n'existe pas.");
                return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }

            Vanille vanille = vanilleOpt.get();
            vanille.setNom(credentials.get("nom"));
            vanille.setDescription(credentials.get("description"));

            String idType = credentials.get("id_type");
            Optional<Type> type = this.typeService.select_Type_By_id(Integer.parseInt(idType));
            type.ifPresent(vanille::setId_type);
            if (images != null && !images.isEmpty()) {
                List<String> imagesUrls = uploadFiles(images);
                String resultat = String.join(",", imagesUrls);
                vanille.setImage(resultat);
            }
            Vanille updatedVanille = this.vanilleService.enregistrerVanille(vanille);
            result.put("data", updatedVanille);
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
    @GetMapping("/selectAll_Vanille")
    public ResponseEntity<HashMap> selectAll_Disponible() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<Vanille> disponibles = this.vanilleService.selectAll_Vanille();
            result.put("data",disponibles);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @GetMapping("/select_Vanille")
    public ResponseEntity<HashMap> select_Disponible() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<Vanille> disponibles = this.vanilleService.select_Vanille();
            result.put("data",disponibles);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<HashMap<String, Object>> getTotalVanille() {
        HashMap<String, Object> result = new HashMap<>();
        try {
            long total = vanilleService.getTotalVanille();
            result.put("total", total);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("Erreur", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
