package fr.diginamic.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "region")
public class Region
{
	@Id
	private int id;
	private String nom;
	
	public Region() { }
	
	public int getId()
	{
		return id;
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}
}
