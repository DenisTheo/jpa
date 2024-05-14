package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entities.Fournisseur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDaoJdbc implements FournisseurDao
{
    private Connection connection = null;
	private static final String url = "jdbc:mariadb://localhost:3306/compta";
	private static final String user = "root";
	private static final String pass = "";
    
    public FournisseurDaoJdbc()
    {
    	openConnection();
    }
    
    public void openConnection()
    {
    	try
    	{
			connection = DriverManager.getConnection(url, user, pass);
            System.out.println("DB Connection Success");
		}catch(SQLException e)
    	{
	        System.out.println("DB Connection Fail");
			e.printStackTrace();
		}
    }
    
    public void closeConnection()
    {
    	try
    	{
    		connection.close();
            System.out.println("DB Connection closed");
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
	
	@Override
	public List<Fournisseur> extraire()
	{
		List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();
		
		try
		{
			String request = "SELECT * FROM fournisseur;";
			
			Statement statement = connection.createStatement();
	        ResultSet result = statement.executeQuery(request);
	        
			while(result.next())
			{
				int id = result.getInt("ID");
	            String nom = result.getString("NOM");

	            Fournisseur fournisseur = new Fournisseur(id, nom);
	            fournisseurs.add(fournisseur);
			}
		}catch(Exception e)
		{
			System.out.println("Error while trying to update data from table");
			e.printStackTrace();
		}
		
		return fournisseurs;
	}

	@Override
	public void insert(Fournisseur fournisseur)
	{
		try
		{
			int affected = sendRequest("INSERT INTO fournisseur (NOM) VALUES (" + fournisseur + ");");

	        if (affected > 0)
	            System.out.println("Successfully inserted values into table");
	        else
	            System.out.println("Error while trying to insert values into table");
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public int update(String ancienNom, String nouveauNom)
	{
		int affected = 0;
		try
		{
			affected = sendRequest("UPDATE fournisseur SET nom = " + nouveauNom + " WHERE nom = " + ancienNom + ";");
			
			if (affected > 0)
		        System.out.println("Successfully updated data from table");
		    else
		        System.out.println("Error while trying to update data from table");
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return affected;
	}

	@Override
	public boolean delete(Fournisseur fournisseur)
	{
		try
		{
			int affected = sendRequest("DELETE FROM fournisseur WHERE nom = " + fournisseur + ";");
		
			if (affected > 0)
			{
				System.out.println("Successfully deleted data from table");
				return true;
			}else
		    {
		        System.out.println("Error while trying to delete data from table");
		        return false;
		    }
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	private int sendRequest(String request) throws SQLException
	{
		int affected = 0;
		
        try
        {
        	Statement statement = connection.createStatement();
            affected = statement.executeUpdate(request);
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return affected;
    }
}
