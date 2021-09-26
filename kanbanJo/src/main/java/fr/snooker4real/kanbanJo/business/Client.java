package fr.snooker4real.kanbanJo.business;

import javax.persistence.*;
import java.util.List;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Le client a un nom
	private String nom;
	
	// Le client a un ensemble de projet
	@OneToMany(mappedBy="client")
	private List<Projet> projets;
	
	@ManyToOne
	private Ville ville;
	
	public Client() {
		// TODO Auto-generated constructor stub
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

	public List<Projet> getProjets() {
		return projets;
	}

	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", ville=" + ville + "]";
	}
	
}