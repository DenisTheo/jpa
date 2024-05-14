package fr.diginamic.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Client
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(length = 255)
	String nom;

	@Column(length = 255)
	String prenom;

	Date dateNaissance;

	@Embedded
	Adresse adresse;

	@ManyToMany
	@JoinTable(name = "client_compte", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "compte_id"))
    private List<Compte> comptes = new ArrayList<>(); 

	@ManyToOne
	@JoinColumn(name = "banque_id")
	Banque banque;

	public Client() { }

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public String getPrenom()
	{
		return prenom;
	}

	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}

	public Date getDateNaissance()
	{
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance)
	{
		this.dateNaissance = dateNaissance;
	}

	public Adresse getAdresse()
	{
		return adresse;
	}

	public void setAdresse(Adresse adresse)
	{
		this.adresse = adresse;
	}

	public List<Compte> getComptes()
	{
		return comptes;
	}

	public void setComptes(List<Compte> comptes)
	{
		this.comptes = comptes;
	}

	public Banque getBanque()
	{
		return banque;
	}

	public void setBanque(Banque banque)
	{
		this.banque = banque;
	}
}
