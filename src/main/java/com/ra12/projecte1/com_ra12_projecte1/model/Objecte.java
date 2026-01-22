package com.ra12.projecte1.com_ra12_projecte1.model;

public class Objecte {
    private Long id;
    private String image_path;
    private String user;
    private String description;
    private String aCanvi;
    private boolean isFav;
    
    public Objecte() {
    }

    public Objecte(Long id, String image_path, String user, String description, String aCanvi, boolean isFav) {
        this.id = id;
        this.image_path = image_path;
        this.user = user;
        this.description = description;
        this.aCanvi = aCanvi;
        this.isFav = isFav;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
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

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean isFav) {
        this.isFav = isFav;
    }

    public String getaCanvi() {
        return aCanvi;
    }

    public void setaCanvi(String aCanvi) {
        this.aCanvi = aCanvi;
    }
}
