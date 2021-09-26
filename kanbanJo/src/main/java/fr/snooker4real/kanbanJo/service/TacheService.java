package fr.snooker4real.kanbanJo.service;

import fr.snooker4real.kanbanJo.business.Colonne;
import fr.snooker4real.kanbanJo.business.Developpeur;
import fr.snooker4real.kanbanJo.business.Tache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface TacheService {

    Tache ajouterTache(String intitule);

    List<Tache> recupererTaches();

    Tache recupererTache(Long id);

    void supprimerTache(Tache tache);

    Tache enregistrerTache(Tache tache);

    Page<Tache> recupererTaches(Pageable pageable);

    Tache ajouterTache(Tache tache);

    Tache editerTache(Tache tache, String description);

    boolean supprimerTache(Long id);

    Page<Tache> recupererTaches(Colonne colonne, Pageable pageable);

    List<Tache> recupererTachesAFaire(Developpeur developpeur);

    List<Tache> recupererTaches(String intitule);

    //int recupererTotalHeuresPrevues(Date dateDebut, Date dateFin);

    Tache deplacerTache(Tache tache, Colonne colonne);

    List<Tache> recupererTachesBetween(Date dateDebut, Date dateFin);
}
