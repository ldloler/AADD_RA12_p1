package com.ra12.projecte1.com_ra12_projecte1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ra12.projecte1.com_ra12_projecte1.dto.UsuarioRequest;
import com.ra12.projecte1.com_ra12_projecte1.dto.UsuarioResponse;
import com.ra12.projecte1.com_ra12_projecte1.service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/trokka")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/usuario")
    public ResponseEntity<String> postUsers(@RequestBody UsuarioRequest usuario) {
        UsuarioResponse finded = usuarioService.getByName(usuario.getNom());

        if (finded != null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Nom d'usuari ja en ús. Escull un altre.");
        }

        int upload = usuarioService.post(usuario);
        if (upload == 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No s'ha pogut afegir l'usuari");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Sha registrat el teu usuari correctament.");
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<UsuarioResponse>> getAllUsers() {
        List<UsuarioResponse> finded = usuarioService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(finded);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<UsuarioResponse> getUserById(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getById(id));
    }

    @GetMapping("/usuario/{nombre}")
    public ResponseEntity<UsuarioResponse> getUserByName(@RequestParam String nombre) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getByName(nombre));
    }
    
    @PutMapping("/usuario/{nombre_nuevo}/{nombre_viejo}")
    public ResponseEntity<String> putUserName(@PathVariable String nombre_nuevo, @PathVariable String nombre_viejo) {
        UsuarioResponse finded = usuarioService.getByName(nombre_viejo);
        if (finded == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No s'ha trobat l'usuari.");
        }

        UsuarioResponse finded_new = usuarioService.getByName(nombre_nuevo);
        if (finded_new != null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nom d'usuari ja està en ús");
        }

        int updated = usuarioService.updateByName(nombre_nuevo, nombre_viejo);
        if (updated == 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No s'ha pogut cambiar el nom.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("S'ha cambiat l'usuari amb èxit.");
    }
    
    @PutMapping("/usuario/{nombre}/{contrasenya}")
    public ResponseEntity<String> putUserPasword(@PathVariable String nombre, @PathVariable String contrasenya) {
        UsuarioResponse finded = usuarioService.getByName(nombre);
        if (finded == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No s'ha trobat l'usuari.");
        }

        int updated = usuarioService.updateByPassword(nombre, contrasenya);
        if (updated == 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No s'ha pogut actualitzar la contrasenya de l'usuari.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Sha actualitzat correctament la contrasenya de l'usuari.");
    }

    @DeleteMapping("/usuario")
    public ResponseEntity<String> deleteUser(@RequestBody UsuarioRequest usuario){
        UsuarioResponse finded = usuarioService.getByUser(usuario);
        if (finded == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No s'ha trobat l'usuari " + usuario.getNom());
        }

        int deleted = usuarioService.delete(usuario);
        if (deleted == 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No s'ha pogut eliminar l'usuari " + usuario.getNom());
        }

        return ResponseEntity.status(HttpStatus.OK).body("S'ha esborrat l'usuari " + usuario.getNom() + " correctament.");
    }
}
