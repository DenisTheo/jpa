package fr.diginamic.jdbc.entities;

public class Fournisseur
{
	private int id;
	private String nom;
	
	public Fournisseur(int id, String nom)
	{
		setId(id);
		setNom(nom);
	}
	
	private void setId(int id)
	{
		this.id = id;
	}
	
	protected void setNom(String nom)
	{
		this.nom = nom;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getNom()
	{
		return nom;
	}
	
	@Override
	public String toString()
	{
		return id + ": " + nom;
	}
}
