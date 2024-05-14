package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.entities.Fournisseur;

import java.sql.SQLException;

public class TestCRUD
{
    private static Connection connection = null;

	public static void main(String[] args)
	{
		String url = "jdbc:mariadb://localhost:3306/compta";
        String user = "root";
        String pass = "";

        try
        {
            Class.forName("org.mariadb.jdbc.Driver");

            try
            {
            	connection = DriverManager.getConnection(url, user, pass);
                System.out.println("DB Connection Success");

                requestInsert("Fournisseur", "(\'La Maison de la Peinture\')");
                requestUpdate("Fournisseur", "SET nom = \'La Maison des Peintures\'", "nom = \'La Maison de la Peinture\'");
                requestDelete("Fournisseur", "nom = \'La Maison des Peintures\'");
                List<Fournisseur> fournisseurs = requestSelect("Fournisseur", "*");
                for (Fournisseur fournisseur : fournisseurs)
                	System.out.println(fournisseur.toString());
            }catch(SQLException e)
            {
                System.out.println("DB Connection Error");
                e.printStackTrace();
            }
        }catch(ClassNotFoundException e)
        {
            System.out.println("Couldn't load JDBC Driver");
            e.printStackTrace();
        }finally
        {
        	try
        	{
	        	if(connection != null && !connection.isClosed())
	            	connection.close();
        	} catch(SQLException e)
        	{
                System.out.println("SQL Error");
                e.printStackTrace();
        	}
        }
	}
	
	private static void requestInsert(String table, String values) throws SQLException
	{
		String structure = getStructure(table);
		
		if (structure != null)
		{
			int affected = sendRequest("INSERT INTO " + table + " " + structure + " VALUES (" + values + ");");
	
	        if (affected > 0)
	            System.out.println("Successfully inserted " + values + " into table " + table);
	        else
	            System.out.println("Error while trying to insert " + values + " into table " + table);
		} else
		{
			System.out.println("Could not define the structure of table " + table + ".");
		}
	}
	
	private static void requestDelete(String table, String condition) throws SQLException
	{// condition doesn't need the WHERE keyword.
		int affected = sendRequest("DELETE FROM " + table + " WHERE " + condition + ";");
	
		if (affected > 0)
	        System.out.println("Successfully deleted data from table " + table);
	    else
	        System.out.println("Error while trying to delete data from table " + table);
	}
	
	private static void requestUpdate(String table, String changes, String condition) throws SQLException
	{// changes must include the SET keyword, but condition doesn't need the WHERE keyword.
		int affected = sendRequest("UPDATE " + table + " " + changes + " WHERE " + condition + ";");
	
		if (affected > 0)
	        System.out.println("Successfully updated data from table " + table);
	    else
	        System.out.println("Error while trying to update data from table " + table);
	}
	
	private static List<Fournisseur> requestSelect(String table, String values) throws SQLException
	{// Because condition is optional
		return requestSelect(table, values, "");
	}
	
	private static List<Fournisseur> requestSelect(String table, String values, String condition) throws SQLException
	{// values doesn't need to include the SET keyword, but condition needs the WHERE keyword, or can be totally empty.
		List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();
		
		try
		{
			String request = "SELECT " + condition + " FROM " + table + ";";
			
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
			System.out.println("Error while trying to update data from table " + table);
			e.printStackTrace();
		}
		
		return fournisseurs;
	}
	
	private static String getStructure(String table)
	{
		String structure = "(";
		
		try
		{
			ResultSet result = connection.getMetaData().getColumns(null, null, table, null);
			
			result.next();// skips primary key, assuming 1st column is the primary key
	        while (result.next())
	        {
	        	structure += result.getString("COLUMN_NAME");
	        	if (!result.isLast())
	            	structure += ", ";
	        }
	    }catch(SQLException e)
		{
	        e.printStackTrace();
	    }
		
		structure += ")";
		
		return structure;
	}
	
	private static int sendRequest(String request) throws SQLException
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
