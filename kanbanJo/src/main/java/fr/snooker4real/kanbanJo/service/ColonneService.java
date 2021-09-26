package fr.snooker4real.kanbanJo.service;

import fr.snooker4real.kanbanJo.business.Colonne;

import java.util.List;

public interface ColonneService {

    /**
     * Cette méthode permet d'ajouter une colonne
     * @param nom de la colonne
     * @return l'objet de type Colonne créé
     */

    Colonne ajouterColonne(String nom);

    List<Colonne> recuperercolonnes();

    Colonne recupererColonne(String nom);

    Colonne recupererColonne(Long id);

    boolean supprimerTaches(Colonne colonne);
}
