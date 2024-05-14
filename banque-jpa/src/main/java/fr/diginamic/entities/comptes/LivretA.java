package fr.diginamic.entities.comptes;

import javax.persistence.Entity;
import fr.diginamic.entities.Compte;

@Entity
public class LivretA extends Compte
{
	Double taux;

	public LivretA() { }

	public Double getTaux()
	{
		return taux;
	}

	public void setTaux(Double taux)
	{
		this.taux = taux;
	}
}
