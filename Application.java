import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
class Application 
{
	private static ArrayList<User> userList = new ArrayList<User>();
	public static void main(String []args)
	{
		int start = 0;
		Scanner reader = new Scanner(System.in);
		while(start == 0)
		{
			System.out.println("Welcome to Bank of R! What Can I Do For You?"
					+ "\n 1. Log In"
					+ "\n 2. Sign Up");
			
			String ans = reader.nextLine();
			if(ans.equals("1") || ans.toLowerCase().equals("log in"))
			{
				LoginMenu();
			}
			start = 1;
		}
	}
	public static User LoginMenu()
	{
		int inMenu = 0;
		Scanner reader = new Scanner(System.in);
		String username = "";
		String password = "";
		User trgtUser = null;
		while(inMenu == 0)
		{
			System.out.println("Username: ");
			username = reader.nextLine();
			if(FindUser(username) != null)
			{
				trgtUser = FindUser(username);
				int askPass = 0;
				while(askPass == 0)
				{
					System.out.println("Password: ");
					password = reader.nextLine();
					if(trgtUser.getPassword().equals(password))
					{
						return trgtUser;
					}
					System.out.println("Sorry, that password is incorrect!");
				}
			}
			System.out.println("Sorry, that user does not exist!");
			
		}
		return null;
	}
	
	public static void SignUpMenu()
	{
		
	}
	public static User FindUser(String uName)
	{
		for (User element : userList)
			if (element.getName().equals(uName))
	               return element;	   
		return null;
	}
}
