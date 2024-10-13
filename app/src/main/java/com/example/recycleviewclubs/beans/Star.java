package com.example.recycleviewclubs.beans; // Assurez-vous que cela correspond à votre structure de package

public class Star {
    private int id;         // Identifiant unique pour chaque étoile
    private String name;    // Nom de l'étoile
    private String img;     // URL de l'image de l'étoile
    private float star;     // Note en étoiles
    private static int comp; // Compteur statique pour générer des IDs uniques

    // Constructeur
    public Star(String name, String img, float star) {
        this.id = ++comp;   // Incrémente le compteur pour assigner un nouvel ID
        this.name = name;    // Initialise le nom
        this.img = img;      // Initialise l'image
        this.star = star;     // Initialise la note
    }

    // Getter pour l'ID
    public int getId() {
        return id;
    }

    // Setter pour l'ID
    public void setId(int id) {
        this.id = id;
    }

    // Getter pour le nom
    public String getName() {
        return name;
    }

    // Setter pour le nom
    public void setName(String name) {
        this.name = name;
    }

    // Getter pour l'image
    public String getImg() {
        return img;
    }

    // Setter pour l'image
    public void setImg(String img) {
        this.img = img;
    }

    // Getter pour la note
    public float getStar() {
        return star;
    }

    // Setter pour la note
    public void setStar(float star) {
        this.star = star;
    }
}
