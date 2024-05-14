package fr.diginamic.entities;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Operation
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	Date dateOperation;
	Double montant;
	String motif;

	@ManyToOne
	@JoinColumn(name = "compte_id")
	Compte compte;

	public Operation() { }

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Date getDateOperation()
	{
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation)
	{
		this.dateOperation = dateOperation;
	}

	public Double getMontant()
	{
		return montant;
	}

	public void setMontant(Double montant)
	{
		this.montant = montant;
	}

	public String getMotif()
	{
		return motif;
	}

	public void setMotif(String motif)
	{
		this.motif = motif;
	}
	
	public Compte getCompte()
	{
		return compte;
	}

	public void setCompte(Compte compte)
	{
		this.compte = compte;
	}
}
