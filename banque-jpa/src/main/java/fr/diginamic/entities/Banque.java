package fr.diginamic.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Banque
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String nom;

	@OneToMany(mappedBy = "banque")
	List<Client> clients;

	public Banque() { }
	
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
	
	public List<Client> getClients()
	{
		return clients;
	}

	public void setClients(List<Client> clients)
	{
		this.clients = clients;
	}
}
