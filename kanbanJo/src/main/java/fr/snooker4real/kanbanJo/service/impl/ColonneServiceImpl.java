package fr.snooker4real.kanbanJo.service.impl;

import fr.snooker4real.kanbanJo.business.Colonne;
import fr.snooker4real.kanbanJo.business.Tache;
import fr.snooker4real.kanbanJo.dao.ColonneDao;
import fr.snooker4real.kanbanJo.dao.TacheDao;
import fr.snooker4real.kanbanJo.service.ColonneService;
import fr.snooker4real.kanbanJo.service.TacheService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColonneServiceImpl implements ColonneService {

    // Nous avons d'un objet de type DAO (la couche en dessous)
    private final ColonneDao colonneDao;
    private final TacheService tacheService;

    public ColonneServiceImpl(ColonneDao colonneDao, TacheService tacheService) {
        super();
        this.colonneDao = colonneDao;
        this.tacheService = tacheService;
    }

    @Override
    public Colonne ajouterColonne(String nom) {
        Colonne colonne = new Colonne(nom);
        // On confie l'objet à la DAO
        colonneDao.save(colonne);
        return colonne;
    }

    @Override
    public List<Colonne> recuperercolonnes() {
        return colonneDao.findAll();
    }

    @Override
    public Colonne recupererColonne(String nom) {
        return colonneDao.findByNom(nom);
    }

    @Override
    public Colonne recupererColonne(Long id) {
        return colonneDao.findById(id).orElse(null);
    }

    @Override
    public boolean supprimerTaches(Colonne colonne) {
        if(colonne != null){
            if (!colonne.getTaches().isEmpty()){
                for(Tache tache : colonne.getTaches()){
                    tacheService.supprimerTache(tache);
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
