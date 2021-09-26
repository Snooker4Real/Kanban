package fr.snooker4real.kanbanJo.dao;

import fr.snooker4real.kanbanJo.business.TypeTache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeTacheDao extends JpaRepository<TypeTache, Long> {

    TypeTache findByNom(String type);
}
