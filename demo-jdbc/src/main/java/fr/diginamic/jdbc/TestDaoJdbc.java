package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entities.Fournisseur;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class TestDaoJdbc
{
    private static Connection connection = null;
    
	public static void main(String[] args)
	{		
		String url = "jdbc:mariadb://localhost:3306/compta";
        String user = "root";
        String pass = "";
		FournisseurDaoJdbc dao = new FournisseurDaoJdbc();
		List<Fournisseur> fournisseurs;

        try // Connecting to the Database
        {
            Class.forName("org.mariadb.jdbc.Driver");

            try
            {
            	connection = DriverManager.getConnection(url, user, pass);
                System.out.println("DB Connection Success");

            }catch(SQLException e)
            {
                System.out.println("DB Connection Error");
                e.printStackTrace();
            }
            
            // TODO
            
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
}
