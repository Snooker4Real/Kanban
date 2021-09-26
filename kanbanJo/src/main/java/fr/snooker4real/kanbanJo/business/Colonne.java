package fr.snooker4real.kanbanJo.business;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Colonne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Une colonne a un nom
	private String nom;
	
	// Une colonne comporte un ensemble de taches
	@OneToMany(mappedBy="colonne")
	@JsonIgnore
	private List<Tache> taches;

	public Colonne() {
		// TODO Auto-generated constructor stub
	}
	
	public Colonne(String nom) {
		this.nom = nom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}

	@Override
	public String toString() {
		return "Colonne [id=" + id + ", nom=" + nom + "]";
	}
	
}