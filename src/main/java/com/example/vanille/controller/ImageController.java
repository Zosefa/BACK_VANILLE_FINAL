package com.example.vanille.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

    private final ResourceLoader resourceLoader;

    public ImageController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/Uploads/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        try {
            Resource resource = resourceLoader.getResource("file:Uploads/" + imageName);
            return ResponseEntity.ok()
                    .contentType(getContentType(resource))
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private MediaType getContentType(Resource resource) {
        String fileName = resource.getFilename();
        if (fileName != null) {
            if (fileName.endsWith(".png")) {
                return MediaType.IMAGE_PNG;
            } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
                return MediaType.IMAGE_JPEG;
            } else if (fileName.endsWith(".gif")) {
                return MediaType.IMAGE_GIF;
            } else if (fileName.endsWith(".mp4")) {
                return MediaType.parseMediaType("video/mp4");
            }
        }
        return MediaType.APPLICATION_OCTET_STREAM;
    }
}


