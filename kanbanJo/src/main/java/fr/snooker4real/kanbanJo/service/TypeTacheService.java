package fr.snooker4real.kanbanJo.service;

import fr.snooker4real.kanbanJo.business.TypeTache;

import java.util.List;

public interface TypeTacheService {

    TypeTache ajouterTypeTache(String string, String string2);

    List<TypeTache> recupererTypesTache();

    TypeTache recupererTypeTache(String type);
}
