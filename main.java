package password;

//ip address must be approved before access regardless of username and password
//need both the microsoft jdbc jar file and mysql connector in the classpath

import java.util.Scanner;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class main {
	
	public static void displayPasswordsFromDatabase(String connectionString)
	{
		Connection con = null;

		try
		{
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionString);
			
			Statement statement = con.createStatement();
			String displayStatement = "SELECT * FROM Passwords";
			ResultSet result = statement.executeQuery(displayStatement);
			while(result.next())
			{
				String website = result.getString("website");
				String password = result.getString("pw");
				System.out.println("Website: " + website + "\tPassword: " + password + "\n");
			}
			//closing both the statement and result set
			result.close();
			statement.close();
			
		}
		catch(SQLException e)
		{
			System.out.println("Retrieval unsuccessful: Cannot connect to the Azure Darabase.");
			e.printStackTrace();
		}
		finally //close the connection
		{
			if(con != null)
			{
				try
				{
					//close the connection
					con.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void addPasswordToDatabase(String connectionString, String website, String pw)
	{
		Connection con = null;
		
		try
		{
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionString);
			
			Statement statement = con.createStatement();
			String insertStatement = "INSERT INTO Passwords (website, pw) VALUES ('" + website + "', '" + pw + "')";
			statement.executeUpdate(insertStatement);
			//closing both the statement and result set
			statement.close();
			
		}
		catch(SQLException e)
		{
			System.out.println("Update unsuccessful: Cannot connect to the Azure Darabase.");
			e.printStackTrace();
		}
		finally //close the connection
		{
			if(con != null)
			{
				try
				{
					//close the connection
					con.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public static void deletePasswordFromDatabase(String connectionString, String website)
	{
		Connection con = null;
		
		try
		{
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionString);
			
			Statement statement = con.createStatement();
			String insertStatement = "DELETE FROM Passwords WHERE website = '" + website + "'";
			int rowsGone = statement.executeUpdate(insertStatement);
			System.out.println(rowsGone + " rows gone.");
			//closing both the statement and result set
			statement.close();
		}
		catch(SQLException e)
		{
			System.out.println("Deletion unsuccessful: Cannot connect to the Azure Darabase.");
			e.printStackTrace();
		}
		finally //close the connection
		{
			if(con != null)
			{
				try
				{
					//close the connection
					con.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public static void updatePasswordFromDatabase(String connectionString, String website, String pw)
	{
		Connection con = null;
		
		try
		{
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionString);
			
			Statement statement = con.createStatement();
			String insertStatement = "UPDATE Passwords SET pw = '" + pw + "' WHERE website = '" + website + "'";
			int rowsUpdated = statement.executeUpdate(insertStatement);
			System.out.println(rowsUpdated + " rows updated.");
			//closing both the statement and result set
			statement.close();
		}
		catch(SQLException e)
		{
			System.out.println("Update unsuccessful: Cannot connect to the Azure Darabase.");
			e.printStackTrace();
		}
		finally //close the connection
		{
			if(con != null)
			{
				try
				{
					//close the connection
					con.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		String connectionString = "(add your link here);";
		Scanner in = new Scanner(System.in);
		String userInputWebsite = "";
		String userInputPassword = "";
		int choice;
		try
		{
			do
			{
				System.out.print("--Password Storage--\n1. Display passwords\n2. Add a password\n3. Delete a password\n4. Update a password\n00. Enter any number to end program\nEnter choice (number): ");
				choice = in.nextInt();
				System.out.println();
				switch(choice)
				{
					case 1: //Display password
						System.out.println("Displaying passwords...\n");
						displayPasswordsFromDatabase(connectionString);
						break;
					case 2: //Add password
						System.out.print("Enter the website: ");
						userInputWebsite = in.next();
						System.out.print("Enter the password: ");
						userInputPassword = in.next();
						addPasswordToDatabase(connectionString, userInputWebsite, userInputPassword);
						System.out.println();
						break;
					case 3: //Delete password
						System.out.print("Enter the website: ");
						userInputWebsite = in.next();
						deletePasswordFromDatabase(connectionString, userInputWebsite);
						System.out.println();
						break;
					case 4: //Update password
						System.out.print("Enter the website: ");
						userInputWebsite = in.next();
						System.out.print("Enter the new password: ");
						userInputPassword = in.next();
						updatePasswordFromDatabase(connectionString, userInputWebsite, userInputPassword);
						System.out.println();
						break;
					default:
						System.out.println("Program ending...");
						break;
				}
			}
			while(choice >= 1 && choice <= 4);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			in.close();
			System.out.println("Program ended.");
		}
	}
}
