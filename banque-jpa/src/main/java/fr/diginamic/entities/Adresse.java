package fr.diginamic.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Adresse
{
	int numero;
	String rue;
	int codePostal;
	String ville;
	
	public Adresse() { }

	public Adresse(int numero, String rue, int codePostal, String ville)
	{
		super();
		this.numero = numero;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public int getNumero()
	{
		return numero;
	}
	
	public void setNumero(int numero)
	{
		this.numero = numero;
	}

	public String getRue()
	{
		return rue;
	}

	public void setRue(String rue)
	{
		this.rue = rue;
	}

	public int getCodePostal()
	{
		return codePostal;
	}

	public void setCodePostal(int codePostal)
	{
		this.codePostal = codePostal;
	}

	public String getVille()
	{
		return ville;
	}

	public void setVille(String ville)
	{
		this.ville = ville;
	}
}
