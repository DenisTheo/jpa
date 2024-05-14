package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class TestUpdate
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

                update("UPDATE fournisseur SET nom = \'La Maison des Peintures\' WHERE nom = \'La Maison de la Peinture\';");
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
	
	protected static void update(String querry)
	{
		int affected = 0;
		try
        {
        	Statement statement = connection.createStatement();
            affected = statement.executeUpdate(querry);
        	
	        if (affected > 0) 
	            System.out.println("Successfully updated data from table");
	        else
	            System.out.println("Error while trying to update data from table");
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
	}
}
