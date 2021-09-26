package fr.snooker4real.kanbanJo.service.impl;

import fr.snooker4real.kanbanJo.business.Developpeur;
import fr.snooker4real.kanbanJo.dao.DeveloppeurDao;
import fr.snooker4real.kanbanJo.service.DeveloppeurService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloppeurServiceImpl implements DeveloppeurService {

    private final DeveloppeurDao developpeurDao;

    public DeveloppeurServiceImpl(DeveloppeurDao developpeurDao) {
        super();
        this.developpeurDao = developpeurDao;
    }

    @Override
    public Developpeur ajouterDeveloppeur(String nom, String prenom) {
        Developpeur developpeur = new Developpeur();
        developpeur.setNom(nom);
        developpeur.setPrenom(prenom);
        developpeurDao.save(developpeur);
        return developpeur;
    }

    @Override
    public Developpeur ajouterDeveloppeur(String prenom) {
        Developpeur developpeur = new Developpeur();
        developpeur.setPrenom(prenom);
        developpeurDao.save(developpeur);
        return developpeur;
    }

    @Override
    public List<Developpeur> recupererDeveloppeurs() {
        return developpeurDao.findAll();
    }

    @Override
    public Developpeur recupererDeveloppeur(Long idDev) {
        return developpeurDao.findById(idDev).orElse(null);
    }
}
