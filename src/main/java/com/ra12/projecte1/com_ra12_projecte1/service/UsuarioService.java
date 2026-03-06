package com.ra12.projecte1.com_ra12_projecte1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ra12.projecte1.com_ra12_projecte1.dto.UsuarioRequest;
import com.ra12.projecte1.com_ra12_projecte1.dto.UsuarioResponse;
import com.ra12.projecte1.com_ra12_projecte1.logs.UsuarioLogs;
import com.ra12.projecte1.com_ra12_projecte1.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioLogs usuarioLogs;

    public int post(UsuarioRequest usuario){
        int posting = usuarioRepository.save(usuario);
        if (posting == 0) {
            usuarioLogs.error("No se ha podido crear el usuario", "UsuarioService", "post");
        }
        else{
            usuarioLogs.info("Se ha podido crear el usuario correctamente", "UsuarioService", "post");
        }
        return posting;
    }

    public List<UsuarioResponse> getAll(){
        List<UsuarioResponse> getting = usuarioRepository.findAll();
        if (getting == null || getting.isEmpty()) {
            usuarioLogs.info("No hay ningun usuario creado", "UsuarioService", "getAll");
        }
        else{
            int num = getting.size();
            usuarioLogs.info(num + " usuarios mostrados por pantalla", "UsuarioService", "getAll");
        }
        return getting;
    }

    public UsuarioResponse getById(Long id){
        List<UsuarioResponse> finded = usuarioRepository.findById(id);
        if (finded == null || finded.isEmpty()) {
            usuarioLogs.error("No existe el usuario con id: " + id, "UsuarioService", "getById");
            return null;
        }
        usuarioLogs.info("Se ha encontrado el usuario con id: " + id, "UsuarioService", "getById");
        return finded.get(0);
    }

    public UsuarioResponse getByName(String nombre){
        List<UsuarioResponse> finded =usuarioRepository.findByName(nombre);
        if (finded == null || finded.isEmpty()) {
            usuarioLogs.error("No se ha encontrado el usuario con el nombre: " + nombre, "UsuarioService", "getByName");
            return null;
        }
        usuarioLogs.info("Se ha encontrado el usuario con el nombre: " + nombre, "UsuarioService", "getByName");
        return finded.get(0);
    }

    public int updateByName(String nombre_nuevo, String nombre_antiguo){
        int updated = usuarioRepository.changeUserName(nombre_nuevo, nombre_antiguo);
        if (updated == 0) {
            usuarioLogs.error("No se ha podido actualizar el nombre de " + nombre_antiguo + " a " + nombre_nuevo, "UsuarioService", "updateByName");
        }
        else{
            usuarioLogs.info("Se ha actualizado el nombre de usuario " + nombre_antiguo + " a " + nombre_nuevo, "UsuarioService", "updateByName");
        }
        return updated;
    }

    public int updateByPassword(String nombre, String contrasenya){
        int updated = usuarioRepository.changePassword(nombre, contrasenya);
        if (updated == 0) {
            usuarioLogs.error("No se ha podido actualizar la contraseña de " + nombre, "UsuarioService", "updateByPassword");
        }
        else{
            usuarioLogs.info("Se ha actualizado la contraseña de " + nombre, "UsuarioService", "updateByPassword");
        }
        return updated;
    }

    public int deletingById(Long id){
        UsuarioResponse finded = usuarioRepository.findById(id).get(0);
        if (finded == null) {
            usuarioLogs.error("No se ha encontrado el usuario con el id: " + id, "UsuarioService", "deletingById");
            usuarioLogs.error("No se ha podido eliminar el usuario con id: " + id, "UsuarioService", "deletingById");
            return 0; 
        }

        int deleted = usuarioRepository.deleteById(id);
        
        if (deleted == 0) {
            usuarioLogs.error("No se ha podido eliminar el usuario con id: " + id, "UsuarioService", "deletingById");
        }
        else{
            usuarioLogs.info("Se ha eliminado correctmanete el usuario con id: " + id, "UsuarioService", "deletingById");
        }
        return deleted;
    }

    public int delete(UsuarioRequest usuario){
        UsuarioResponse finded = usuarioRepository.findByUser(usuario).get(0);
        if (finded == null) {
            usuarioLogs.error("No se ha encontrado el usuario: " + usuario.getNombre() + ",  " + usuario.getEmail(), "UsuarioService", "delete");
            usuarioLogs.error("No se ha podido eliminar el usuario: " + usuario.getNombre() + ", " + usuario.getEmail(), "UsuarioService", "delete");
            return 0;
        }

        int deleted = usuarioRepository.deleteUser(usuario);

        if (deleted == 0) {
            usuarioLogs.error("No se ha podido eliminar el usuario: " + usuario.getNombre() + ", " + usuario.getEmail(), "UsuarioService", "delete");
        }
        else{
            usuarioLogs.info("Se ha podido eliminar el usuario: " + usuario.getNombre() + ", " + usuario.getEmail(), "UsuarioService", "delete");
        }
        return deleted;
    }

    public UsuarioResponse getByUser(UsuarioRequest usuario){
        List<UsuarioResponse> finded = usuarioRepository.findByUser(usuario);
        if (finded == null || finded.isEmpty()) {
            usuarioLogs.error("No existe el usuario: " + usuario.getNombre() + ", " + usuario.getEmail(), "UsuarioService", "getByUser");
            return null;
        }
        else{
            usuarioLogs.info("Se ha encontrado el usuario: " + usuario.getNombre() + ", " + usuario.getEmail(), "UsuarioService", "getByUser");
        }
        return finded.get(0);
    }
}
