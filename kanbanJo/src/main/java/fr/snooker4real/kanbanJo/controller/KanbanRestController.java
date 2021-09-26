package fr.snooker4real.kanbanJo.controller;

import fr.snooker4real.kanbanJo.business.Colonne;
import fr.snooker4real.kanbanJo.business.Developpeur;
import fr.snooker4real.kanbanJo.business.Tache;
import fr.snooker4real.kanbanJo.service.ColonneService;
import fr.snooker4real.kanbanJo.service.DeveloppeurService;
import fr.snooker4real.kanbanJo.service.TacheService;
import fr.snooker4real.kanbanJo.service.TypeTacheService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("api/")
public class KanbanRestController {
    private Random random;
    private final TacheService tacheService;
    private final TypeTacheService typeTacheService;
    private final ColonneService colonneService;
    private final DeveloppeurService developpeurService;


    public KanbanRestController(TacheService tacheService, TypeTacheService typeTacheService, ColonneService colonneService, DeveloppeurService developpeurService) {
        this.random = new Random();
        this.tacheService = tacheService;
        this.typeTacheService = typeTacheService;
        this.colonneService = colonneService;
        this.developpeurService = developpeurService;
    }

    /**
     * méthode (A)
     * Cette méthode qui ajoute une tâche en précisant son intitulé et le type de la tâche.
     * La méthode choisira un développeur au hasard et un nombre d'heures prévues entre 1 et
     * 144. La tâche sera ajoutée sur la colonne 1 : "A faire".
     * Exemple : http://localhost:8080/taches/Corriger%20CSS/Bug
     * @param nom nom de la tache
     * @param type type de la tache
     * @return Tache ajoutée
     */
    @PostMapping("taches/{nom}/{type}")
    public Tache ajouterTache(@PathVariable String nom, @PathVariable String type){
        Tache tache = new Tache();
        tache.setIntitule(nom);
        tache.setTypeTache(typeTacheService.recupererTypeTache(type));
        tache.setColonne(colonneService.recupererColonne("A faire"));
        Long idDev = (long)random.nextInt(144 - 1) + 1;
        Developpeur dev = developpeurService.recupererDeveloppeur(idDev);
        if (dev!=null){
            tache.getDeveloppeurs().add(dev);
        }
        int nbHeures = random.nextInt(100 - 1) + 1;
        tache.setNbHeuresEstimees(nbHeures);
        return tacheService.ajouterTache(tache);
    }

    /**
     * méthode (B)
     * Cette méthode qui permet d'obtenir toutes les informations sur une tâche
     * précisé dans l'URL
     * Exemple : http://localhost:8080/api/taches/2
     * @param id de la tache
     * @return Tache récupérée
     */
    @GetMapping("taches/{id}")
    Tache getTache(@PathVariable Long id){
        return tacheService.recupererTache(id);
    }

    /**
     * méthode (C)
     * Cette méthode permet de mettre à jour l'intitulé d'une tâche dont l'id est
     * précisé sur l'URL
     * Exemple : http://localhost:8080/api/taches/2/Corriger%20JSP%20index
     * @param id de l'intitulé
     * @param description
     * @return
     */
    @PutMapping("taches/{id}/{description}")
    Tache editerTache(@PathVariable Long id, @PathVariable String description){
        Tache tache = tacheService.recupererTache(id);
        return tacheService.editerTache(tache, description);
    }

    /**
     * methode (D)
     * Cette méthode permettant de supprimer une tâche en précisant son id
     * Exemple : http://localhost:8080/api/taches/2
     * @param id id de la taâche à supprimer
     * @return Tache supprimé
     */
    @DeleteMapping("taches/{id}")
    boolean supprimerTache(@PathVariable Long id){
        return tacheService.supprimerTache(id);
    }

    /**
     * méthode (E)
     * Cette méthode permettant d'obtenir une page de toutes les tâches
     * placées dans une colonne
     * Exemple : http://localhost:8080/api/colonnes/2/taches?page=1&size=5
     * @param id de la Colonne
     * @param pageable pages à afficher
     * @return Page de Tache à afficher
     */
    @GetMapping("colonnes/{id}/taches")
    Page<Tache> recupererTachesParColonne(@PathVariable Long id,
                                          @PageableDefault(size = 10,
                                                  page = 0,
                                                  sort = "dateCreation",
                                                  direction = Sort.Direction.DESC)
                                                  Pageable pageable){
        System.out.println(id);
        Colonne colonne = colonneService.recupererColonne(id);
        return tacheService.recupererTaches(colonne, pageable);
    }

    /**
     * méthode (F)
     * Cette méthode permettant d'obtenir les tâches ayant le statut "A faire"
     * et confiées ç un développeur en particulier
     * Exemple : http://localhost:8080/api/developpeurs/2/tachesAFaire
     * @param id du developpeur
     * @return Tache trouvées
     */
    @GetMapping("developpeurs/{id}/tachesAfaire")
    List<Tache> recupererTachesAFaire(@PathVariable Long id){
        Developpeur developpeur = developpeurService.recupererDeveloppeur(id);
        System.out.println(developpeur);
        return tacheService.recupererTachesAFaire(developpeur);
    }

    /**
     * méthode (G)
     * Cette méthode permet d'obtenir toutes les tâches dont l'intitulé
     * contient le mot précisé dans l'URL
     * @param intitule
     * @return Taches trouvées
     */
    @GetMapping("taches")
    List<Tache> recupererTaches(@RequestParam String intitule){
        return tacheService.recupererTaches(intitule);
    }

    /**
     * H) une méthode permettant de déterminer le total des heures prévues pour les
     tâchées créées entre deux dates données en paramètre
     * @param dateDebut
     * @param dateFin
     * @return
     */
    @GetMapping("totalHeuresPrevues")
    public int recupererTotalHeuresPrevuesBetween(
            @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(name="dateDebut") Date dateDebut,
            @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(name="dateFin") Date dateFin) {
        int totalHeuresPrevues = 0;
        List<Tache> taches = tacheService.recupererTachesBetween(dateDebut, dateFin);
        System.out.println(taches);
        for(Tache tache: taches) {
            totalHeuresPrevues += tache.getNbHeuresEstimees();
        }
        return totalHeuresPrevues;
    }

    /**
     * méthode (I)
     * Cette méthode permet de supprimer toutes les tâches d'une colonne
     * Exemple: http://localhost:8080/api/colonnes/1/taches
     * @param id
     * @return Colonnes supprimées?
     */
    @DeleteMapping("colonnes/{id}/taches")
    public boolean supprimerTaches(@PathVariable Long id){
        Colonne colonne = colonneService.recupererColonne(id);
        return colonneService.supprimerTaches(colonne);
    }

    /**
     * méthode (J)
     * Cette méthode permet de gérer le déplacement d'une tâche effectuée sur le
     * tacleau Kanban de la page Web de l'application
     * Exemple: http://localhost:8080/api/taches/1/colonnes/1
     * @param idTache
     * @param idColonne
     * @return
     */
    @PutMapping("taches/{idTache}/colonnes/{idColonne}")
    public Tache deplacerTache(@PathVariable Long idTache, @PathVariable Long idColonne){
        Tache tache = tacheService.recupererTache(idTache);
        Colonne colonne = colonneService.recupererColonne(idColonne);
        return tacheService.deplacerTache(tache, colonne);
    }
}


