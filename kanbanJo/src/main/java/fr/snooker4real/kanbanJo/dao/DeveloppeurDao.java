package fr.snooker4real.kanbanJo.dao;

import fr.snooker4real.kanbanJo.business.Colonne;
import fr.snooker4real.kanbanJo.business.Developpeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloppeurDao extends JpaRepository<Developpeur, Long> {

}
