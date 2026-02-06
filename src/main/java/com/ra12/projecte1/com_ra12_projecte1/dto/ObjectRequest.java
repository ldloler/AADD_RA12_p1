package com.ra12.projecte1.com_ra12_projecte1.dto;

public class ObjectRequest {
    private String titulo;
    private String user;
    private String description;
    private String aCanvi;
    private boolean isFav;
    
    public ObjectRequest() {
    }
    
    public ObjectRequest(String titulo, String image_path, String user, String description, String aCanvi,
            boolean isFav) {
        this.titulo = titulo;
        this.user = user;
        this.description = description;
        this.aCanvi = aCanvi;
        this.isFav = isFav;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getaCanvi() {
        return aCanvi;
    }

    public void setaCanvi(String aCanvi) {
        this.aCanvi = aCanvi;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean isFav) {
        this.isFav = isFav;
    }

    
}
