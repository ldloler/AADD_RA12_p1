package com.ra12.projecte1.com_ra12_projecte1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ra12.projecte1.com_ra12_projecte1.dto.UsuarioRequest;
import com.ra12.projecte1.com_ra12_projecte1.dto.UsuarioResponse;
import com.ra12.projecte1.com_ra12_projecte1.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public int post(UsuarioRequest usuario){
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioResponse> getAll(){
        return usuarioRepository.findAll();
    }

    public UsuarioResponse getById(Long id){
        List<UsuarioResponse> finded =usuarioRepository.findById(id);
        if (finded == null || finded.isEmpty()) {
            return null;
        }
        return finded.get(0);
    }

    public UsuarioResponse getByName(String nombre){
        List<UsuarioResponse> finded =usuarioRepository.findByName(nombre);
        if (finded == null || finded.isEmpty()) {
            return null;
        }
        return finded.get(0);
    }

    public int updateByName(String nombre_nuevo, String nombre_antiguo){
        return usuarioRepository.changeUserName(nombre_nuevo, nombre_antiguo);
    }

    public int updateByPassword(String nombre, String contrasenya){
        return usuarioRepository.changePassword(nombre, contrasenya);
    }

    public int delete(UsuarioRequest usuario){
        return usuarioRepository.deleteUser(usuario);
    }

    public UsuarioResponse getByUser(UsuarioRequest usuario){
        List<UsuarioResponse> finded =usuarioRepository.findByUser(usuario);
        if (finded == null || finded.isEmpty()) {
            return null;
        }
        return finded.get(0);
    }
}
