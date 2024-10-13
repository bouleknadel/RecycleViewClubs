package com.example.recycleviewclubs.service; // Assurez-vous que cela correspond à votre structure de package

import com.example.recycleviewclubs.beans.Star; // Assurez-vous d'importer la classe Star
import com.example.recycleviewclubs.dao.IDao; // Assurez-vous d'importer l'interface IDao
import java.util.ArrayList;
import java.util.List;

// Classe de service pour gérer les opérations sur les étoiles
public class StarService implements IDao<Star> {
    private List<Star> stars;          // Liste des étoiles
    private static StarService instance; // Instance unique (Singleton)

    // Constructeur privé pour empêcher l'instanciation externe
    private StarService() {
        this.stars = new ArrayList<>();
    }

    // Méthode pour obtenir l'instance unique de StarService
    public static StarService getInstance() {
        if (instance == null) {
            instance = new StarService();
        }
        return instance;
    }

    @Override
    public boolean create(Star o) {
        return stars.add(o); // Ajoute une étoile à la liste
    }

    @Override
    public boolean update(Star o) {
        for (Star s : stars) {
            if (s.getId() == o.getId()) { // Utilisation de getId pour la comparaison
                s.setImg(o.getImg());
                s.setName(o.getName());
                s.setStar(o.getStar());
                return true; // Retourne true si la mise à jour a réussi
            }
        }
        return false; // Retourne false si l'étoile n'a pas été trouvée
    }

    @Override
    public boolean delete(Star o) {
        return stars.remove(o); // Supprime l'étoile de la liste
    }

    @Override
    public Star findById(int id) {
        for (Star s : stars) {
            if (s.getId() == id) {
                return s; // Retourne l'étoile correspondante
            }
        }
        return null; // Retourne null si aucune étoile n'a été trouvée
    }

    @Override
    public List<Star> findAll() {
        return stars; // Retourne la liste de toutes les étoiles
    }
}
