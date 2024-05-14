package fr.diginamic;

import fr.diginamic.entities.Livre;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestLivre
{
	public static void main(String[] args)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("recensement");
		EntityManager em = emf.createEntityManager();
		//EntityTransaction transaction = em.getTransaction();
		
		//transaction.begin();
		
		Livre book = em.find(Livre.class, 1);
		
		if(book != null)
			System.out.println(book.getId() + ": " + book.getTitre() + ", " + book.getAuteur() + ".");
		
		//transaction.commit();
	}
}
