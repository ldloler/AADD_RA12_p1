package com.ra12.projecte1.com_ra12_projecte1.model;

public class Usuario {
    private Long id;
    private String nom;
    private String contrasenya;
    
    public Usuario() {
    }

    public Usuario(Long id, String nom, String contrasenya) {
        this.id = id;
        this.nom = nom;
        this.contrasenya = contrasenya;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
