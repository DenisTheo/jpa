package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnexionJDBC
{
	public static void main(String[] args)
	{
        String url = "jdbc:mariadb://localhost:3306/diginamic";
        String user = "root";
        String pass = "";
        Connection connection;

        try
        {
            Class.forName("org.mariadb.jdbc.Driver");

            try
            {
            	connection = DriverManager.getConnection(url, user, pass);
                System.out.println("DB Connection Success");
                connection.close();
            }catch(SQLException e)
            {
                System.err.println("DB Connection Error");
                e.printStackTrace();
            }
        }catch(ClassNotFoundException e)
        {
            System.err.println("Couldn't load JDBC Driver");
            e.printStackTrace();
        }
	}
}
