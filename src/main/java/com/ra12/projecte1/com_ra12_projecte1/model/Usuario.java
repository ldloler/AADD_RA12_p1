package com.ra12.projecte1.com_ra12_projecte1.model;

public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private String contrasenya;
    
    public Usuario() {
    }

    public Usuario(Long id, String nombre, String email, String contrasenya) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasenya = contrasenya;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNom(String nombre) {
        this.nombre = nombre;
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
