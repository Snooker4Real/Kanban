package fr.snooker4real.kanbanJo.service.impl;

import fr.snooker4real.kanbanJo.business.Colonne;
import fr.snooker4real.kanbanJo.business.Developpeur;
import fr.snooker4real.kanbanJo.business.Tache;
import fr.snooker4real.kanbanJo.dao.ColonneDao;
import fr.snooker4real.kanbanJo.dao.DeveloppeurDao;
import fr.snooker4real.kanbanJo.dao.TacheDao;
import fr.snooker4real.kanbanJo.dao.TypeTacheDao;
import fr.snooker4real.kanbanJo.service.TacheService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class TacheServiceImpl implements TacheService {

    private final TacheDao tacheDao;

    private final ColonneDao colonneDao;

    private final DeveloppeurDao developpeurDao;

    private final TypeTacheDao typeTacheDao;

    private static Random random = new Random();

    public TacheServiceImpl(TacheDao tacheDao, ColonneDao colonneDao, DeveloppeurDao developpeurDao,
                            TypeTacheDao typeTacheDao) {
        super();
        this.tacheDao = tacheDao;
        this.colonneDao = colonneDao;
        this.developpeurDao = developpeurDao;
        this.typeTacheDao = typeTacheDao;
    }

    @Override
    public Tache ajouterTache(String intitule) {
        Tache tache = new Tache(intitule);
        tache.setNbHeuresEstimees(1 + random.nextInt(100));
        tache.setNbHeuresReelles(1 + random.nextInt(100));
        tache.setColonne(colonneDao.findAll().get(0));
        tache.setTypeTache(typeTacheDao.findAll().get(random.nextInt((int) typeTacheDao.count())));
        List<Developpeur> developpeurs = new ArrayList<>();
        developpeurs.add(developpeurDao.findAll().get(random.nextInt((int) developpeurDao.count())));
        tache.setDeveloppeurs(developpeurs);
        tacheDao.save(tache);
        return tache;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tache> recupererTaches() {

        return tacheDao.findAll();
    }

    /**
     * Cette méthode récupère une tâche dont l'id est donné en paramètre
     */
    @Override
    @Transactional(readOnly = true)
    public Tache recupererTache(Long id) {
        return tacheDao.findById(id).orElse(null);
    }

    @Override
    public void supprimerTache(Tache tache) {
        tacheDao.delete(tache);
    }

    @Override
    public Tache enregistrerTache(Tache tache) {
        return tacheDao.save(tache);
    }

    @Override
    public Page<Tache> recupererTaches(Pageable pageable) {
        return tacheDao.findAll(pageable);
    }

    @Override
    public Tache ajouterTache(Tache tache) {
        return tacheDao.save(tache);
    }

    @Override
    public Tache editerTache(Tache tache, String description) {
        tache.setIntitule(description);
        return tacheDao.save(tache);
    }

    @Override
    public boolean supprimerTache(Long id) {
        if(recupererTache(id) !=null){
            tacheDao.deleteById(id);
            return recupererTache(id) ==null;
        }else{
            return false;
        }

    }

    @Override
    public Page<Tache> recupererTaches(Colonne colonne, Pageable pageable) {
        return tacheDao.findAllByColonne(colonne,pageable);
    }

    @Override
    public List<Tache> recupererTachesAFaire(Developpeur developpeur) {
        return tacheDao.findAllTachesAFaire(developpeur);
    }

    @Override
    public List<Tache> recupererTaches(String intitule) {
        return tacheDao.findAllByIntituleContains(intitule);
    }

/*    @Override
    public int recupererTotalHeuresPrevues(Date dateDebut, Date dateFin) {
        return tacheDao.findTotalHeuresPrevues(dateDebut,dateFin);
    }*/

    @Override
    public Tache deplacerTache(Tache tache, Colonne colonne) {
        tache.setColonne(colonne);
        return tacheDao.save(tache);
    }

    @Override
    public List<Tache> recupererTachesBetween(Date dateDebut, Date dateFin) {
        return tacheDao.findAllByDateCreationBetween(dateDebut, dateFin);
    }
}