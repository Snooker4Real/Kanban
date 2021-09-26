package fr.snooker4real.kanbanJo.dao;

import fr.snooker4real.kanbanJo.business.Colonne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColonneDao extends JpaRepository<Colonne, Long> {

    Colonne findByNom(String nom);
}
