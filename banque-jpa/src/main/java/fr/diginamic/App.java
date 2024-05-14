package fr.diginamic;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import fr.diginamic.entities.Adresse;
import fr.diginamic.entities.Banque;
import fr.diginamic.entities.Client;
import fr.diginamic.entities.comptes.AssuranceVie;
import fr.diginamic.entities.comptes.LivretA;
import fr.diginamic.entities.comptes.Virement;

public class App
{
	public static void main(String[] args)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		Adresse adresse = new Adresse(17, "beaucaire",75000, "Paris");
		List<Client> clients = new ArrayList<Client>();
		List<Banque> banques = new ArrayList<Banque>();
		List<Virement> virementsAssuranceVie = new ArrayList<Virement>();
		List<Virement> virementsLivret = new ArrayList<Virement>();
		transaction.begin();
		
		Client client = new Client();
		client.setAdresse(adresse);
		client.setDateNaissance(new java.util.Date(90, 7, 6));
		client.setNom("Doe");
		client.setPrenom("John");
		clients.add(client);
		
		Banque banque = new Banque();
		banque.setNom("harry");
		banques.add(banque);
		banque.setClients(clients);
		client.setBanque(banque);
		
		LivretA livretA = new LivretA();
		livretA.setNumero("1096");
		livretA.setSolde(150.1);
		livretA.setTaux(2.5);
		livretA.setClients(clients);
		
		AssuranceVie assuranceVie = new AssuranceVie();
		assuranceVie.setClients(clients);
		assuranceVie.setDateFin(new java.util.Date(150, 7, 6));
		assuranceVie.setNumero("65");
		assuranceVie.setSolde(150.3);
		assuranceVie.setTaux(1.2);
		
		Virement virementAssuranceVie = new Virement();
		virementAssuranceVie.setDateOperation(new java.util.Date(150, 5, 3));
		virementAssuranceVie.setMontant(50.0);
		virementAssuranceVie.setMotif("Credited Life Insurance");
		virementAssuranceVie.setBeneficiaire("Life Insurance Account");
		virementAssuranceVie.setCompte(assuranceVie);
		virementsAssuranceVie.add(virementAssuranceVie);

		Virement virementLivretA = new Virement();
		virementLivretA.setDateOperation(new java.util.Date(150, 6, 5));
		virementLivretA.setMontant(30.0);
		virementLivretA.setMotif("Credited Livret A");
		virementLivretA.setBeneficiaire("Livret A Account");
		virementLivretA.setCompte(livretA);
		virementsLivret.add(virementLivretA);

		em.persist(virementAssuranceVie);
		em.persist(virementLivretA);
		
		em.persist(assuranceVie);
		em.persist(livretA);
		em.persist(banque);
		em.persist(client);
		transaction.commit();	
	}
}