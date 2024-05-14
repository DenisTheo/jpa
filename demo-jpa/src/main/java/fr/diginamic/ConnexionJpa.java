package fr.diginamic;

import fr.diginamic.entities.Region;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ConnexionJpa
{
	public static void main(String[] args)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("recensement");
		EntityManager em = emf.createEntityManager();
		
		Region r = em.find(Region.class, 1);
		
		if(r != null)
			System.out.println(r.getId() + ": " + r.getNom());
	}
}
