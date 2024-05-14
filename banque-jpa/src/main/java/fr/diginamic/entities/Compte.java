package fr.diginamic.entities;

import java.util.List;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Compte
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(length = 255)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	String numero;
	
	Double solde;

	@OneToMany(mappedBy = "compte")
	List<Operation> operations;

	@ManyToMany(mappedBy = "comptes")
	List<Client> clients;

	public Compte()
	{
		super();
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getNumero()
	{
		return numero;
	}

	public void setNumero(String numero)
	{
		this.numero = numero;
	}

	public Double getSolde()
	{
		return solde;
	}

	public void setSolde(Double solde)
	{
		this.solde = solde;
	}
	
	public List<Operation> getOperations()
	{
		return operations;
	}

	public void setOperations(List<Operation> operations)
	{
		this.operations = operations;
	}

	public List<Client> getClients()
	{
		return clients;
	}
	
	public void setClients(List<Client> clients)
	{
		this.clients = clients;
	}
}
