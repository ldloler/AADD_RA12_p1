package com.ra12.projecte1.com_ra12_projecte1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ra12.projecte1.com_ra12_projecte1.model.Objecte;
import com.ra12.projecte1.com_ra12_projecte1.service.ObjectService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/trokka")
public class ObjectController {
    @Autowired
    ObjectService objectService;

    @PostMapping("/objecte/{id}/image")
    public ResponseEntity<String> postImageObject(@PathVariable Long id, @RequestParam MultipartFile image) {
        String uploaded = objectService.uploadingImage(id, image);
        if (uploaded == null || uploaded.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No s'ha pogut afegir a imatge a la base de dades");
        }
        return ResponseEntity.status(HttpStatus.OK).body("S'ha afegit correctament la imatge a la Base de Dades. S'ha guardat la imatge a "
         + uploaded);
    }

    @PutMapping("/objecte/{id}")
    public ResponseEntity<String> putObjecte(@PathVariable Long id, @RequestBody Objecte objecte) {
        int updated = objectService.updatingObjecte(id, objecte);
        if(updated == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No s'ha pogut actualitzar l'objecte.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("S'ha actualitzat correctament l'objecte.");
    }

    @DeleteMapping("/objecte/{id}")
    public ResponseEntity<String> deleteObjectById(@PathVariable Long id){
        int delete = objectService.deletingById(id);
        if(delete == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No s'ha pogut eliminar l'objecte.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("S'ha eliminat l'objecte correctament");
    }

    @DeleteMapping("/objecte")
    public ResponseEntity<String> deleteObjects(){
        int delete = objectService.deletingAll();
        if(delete == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No s'ha pogut eliminar tots els objectes.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("S'ha eliminat tots els objectes");
    }
    
}
