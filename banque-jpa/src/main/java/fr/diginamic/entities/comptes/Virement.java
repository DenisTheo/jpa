package fr.diginamic.entities.comptes;

import javax.persistence.Entity;
import fr.diginamic.entities.Operation;

@Entity
public class Virement extends Operation
{
	String beneficiaire;

	public Virement()
	{
		super();
	}

	public String getBeneficaire()
	{
		return beneficiaire;
	}

	public void setBeneficiaire(String beneficiaire)
	{
		this.beneficiaire = beneficiaire;
	}	
}
