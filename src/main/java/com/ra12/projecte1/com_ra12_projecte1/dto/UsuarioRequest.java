package com.ra12.projecte1.com_ra12_projecte1.dto;

public class UsuarioRequest {
    private String nombre;
    private String email;
    private String contrasenya;
    
    public UsuarioRequest() {
    }

    public UsuarioRequest(String nombre, String email, String contrasenya) {
        this.nombre = nombre;
        this.email = email;
        this.contrasenya = contrasenya;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nom) {
        this.nombre = nom;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
