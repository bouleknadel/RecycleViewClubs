package com.example.recycleviewclubs.dao; // Assurez-vous que cela correspond à votre structure de package

import java.util.List;

// Interface générique pour les opérations de base de données
public interface IDao<T> {
    boolean create(T o);           // Crée un nouvel objet
    boolean update(T o);           // Met à jour un objet existant
    boolean delete(T o);           // Supprime un objet
    T findById(int id);            // Trouve un objet par son ID
    List<T> findAll();             // Récupère tous les objets
}
