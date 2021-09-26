package fr.snooker4real.kanbanJo.service.impl;

import fr.snooker4real.kanbanJo.business.TypeTache;
import fr.snooker4real.kanbanJo.dao.TypeTacheDao;
import fr.snooker4real.kanbanJo.service.TypeTacheService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeTacheServiceImpl implements TypeTacheService {

    TypeTacheDao typeTacheDao;

    public TypeTacheServiceImpl(TypeTacheDao typeTacheDao) {
        super();
        this.typeTacheDao = typeTacheDao;
    }

    @Override
    public TypeTache ajouterTypeTache(String nom, String couleur) {
        return typeTacheDao.save(new TypeTache(nom, couleur));
    }

    @Override
    public List<TypeTache> recupererTypesTache() {
        return typeTacheDao.findAll();
    }

    @Override
    public TypeTache recupererTypeTache(String type) {
        return typeTacheDao.findByNom(type);
    }
}
