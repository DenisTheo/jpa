package fr.diginamic.entities.comptes;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import fr.diginamic.entities.Compte;

@Entity
public class AssuranceVie extends Compte
{
    @Temporal(TemporalType.DATE)
	Date dateFin;
	Double taux;
	
	public AssuranceVie() { }

	public Date getDateFin()
	{
		return dateFin;
	}

	public void setDateFin(Date dateFin)
	{
		this.dateFin = dateFin;
	}

	public Double getTaux()
	{
		return taux;
	}

	public void setTaux(Double taux)
	{
		this.taux = taux;
	}
}
