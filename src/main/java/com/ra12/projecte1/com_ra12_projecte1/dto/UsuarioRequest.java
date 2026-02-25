package com.ra12.projecte1.com_ra12_projecte1.dto;

public class UsuarioRequest {
    private String nom;
    private String contrasenya;
    
    public UsuarioRequest() {
    }

    public UsuarioRequest(String nom, String contrasenya) {
        this.nom = nom;
        this.contrasenya = contrasenya;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    
}
