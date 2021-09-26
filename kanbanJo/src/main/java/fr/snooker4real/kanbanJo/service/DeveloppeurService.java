package fr.snooker4real.kanbanJo.service;

import fr.snooker4real.kanbanJo.business.Developpeur;

import java.util.List;

public interface DeveloppeurService {

    Developpeur ajouterDeveloppeur(String nom, String prenom);

    Developpeur ajouterDeveloppeur(String string);

    List<Developpeur> recupererDeveloppeurs();

    Developpeur recupererDeveloppeur(Long idDev);
}
