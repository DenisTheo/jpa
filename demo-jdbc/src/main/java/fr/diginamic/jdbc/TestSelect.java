package fr.diginamic.jdbc;

import fr.diginamic.jdbc.entities.Fournisseur;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestSelect
{
    private static Connection connection = null;

	public static void main(String[] args)
	{
		String url = "jdbc:mariadb://localhost:3306/compta";
        String user = "root";
        String pass = "";
        List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();

        try
        {
            Class.forName("org.mariadb.jdbc.Driver");

            try
            {
            	connection = DriverManager.getConnection(url, user, pass);
                System.out.println("DB Connection Success");

                fournisseurs = select("SELECT * FROM fournisseur;");
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
        
        for(Fournisseur fournisseur : fournisseurs)
        	System.out.println(fournisseur.toString());
	}
	
	protected static List<Fournisseur> select(String querry)
	{
        List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();
		int affected = 0;
		
		try
        {
        	Statement statement = connection.createStatement();
            affected = statement.executeUpdate(querry);
        	
	        if (affected > 0) 
	            System.out.println("Successfully fetched data from table");
	        else
	            System.out.println("Error while trying to fetch data from table");
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
		
		return fournisseurs;
	}
}
