import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
class Application 
{
	/* =======================Bank of R Application=================================
	 * GOALS OF APPLICATION
	 * 	-Fully Mirror bank applications
	 * 	-Allows users to make accounts, sign in, withdraw, deposit, etc
	 * 	-Use multithreading to synchronize multiple transactions
	 * 	-Properly use data structure techniques to store and manipulate data efficiently
	 *  -Make my github look nicer
	 *  
	 *  
	 *  v0.2
	 *  Changes:
	 *  	-Made new AccountList class to read and write accounts to file
	 *  	-moved Find User Method to AccountList
	 *  	-added add and delete methods using readers and writers.
	 *  Todo:
	 *  	-Finish AccountList class
	 *  	-Make main menu
	 *  	-Make admin menu (ability to delete accounts, move money freely, etc)
	 *  	-Make deposit and withdraw transactions
	 *  	-Allow different types of accounts
	 *  	-Implement database, multithreading, etc
	 *  	-Move all this to the readMe.
	 *  
	 *  
	 *  
	 *  v0.1
	 *  Changes:
	 *  	-Finished Sign Up Menu, still need testing.
	 *  	-Added description and comments
	 *  	-Finished Log In Menu
	 *  Todo:
	 *  	-Make main menu
	 *  	-Make deposit and withdraw transactions
	 *  	-Allow different types of accounts
	 *  	-Implement database, multithreading, etc
	 *  	-Move all this to the readMe.
	 */
	
	private static AccountList userList = new AccountList();
	public static void main(String []args)
	{
		int start = 0;
		Scanner reader = new Scanner(System.in);
		while(start == 0)
		{
			System.out.println("Welcome to Bank of R! What Can I Do For You?"
					+ "\n 1. Log In"
					+ "\n 2. Sign Up"
					+ "\n 0. Exit");
			
			String ans = reader.nextLine();
			if(ans.equals("0") || ans.toLowerCase().equals("exit"))
			{
				try {
					userList.save();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Thanks for using Bank of R! Have a great day!");
				start = 1;
			}
			
			
			//Login Option
			else if(ans.equals("1") || ans.toLowerCase().equals("log in"))
			{
				User newUser = loginMenu();
				if(newUser != null)
				{
					//MainMenu(newUser)
				}
			}
			
			
			
			
			
			//Sign Up Option
			else if(ans.equals("2") || ans.toLowerCase().equals("sign up"))
			{
				signUpMenu();
				System.out.println("Thanks for Signing Up With Bank of R! Please Log In By Selecting Choice 1");
			}
			else
			{
				System.out.println("Sorry that is not a valid choice, please try again!");
			}
		}
	}
	
	//--------------LoginMenu------------------
	//@return User object to initiate Main Menu
	//Will return null if user wants to exit and sign up instead
	public static User loginMenu()
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
			System.out.println("Password: ");
			password = reader.nextLine();
			trgtUser = userList.findUser(username, password);
			if(trgtUser!= null)
			{
				return trgtUser;
			}
			System.out.println("Sorry, that user does not exist or your password is wrong!"
					+ "\n Would you like to make a new account instead (Y/N)?");
			String ans = reader.nextLine();
			if(ans.toLowerCase().equals("yes") || ans.toLowerCase().equals("y"))
			{
				return null;
			}
		}
		return null;
	}
	
	//------------Sign Up Menu---------------
	//@return Boolean, true if an account was created, false if not.
	//Has proper error checks, can change depending on if we get hacked or not
	public static Boolean signUpMenu()
	{
		Scanner reader = new Scanner(System.in);
		int running = 0;
		while(running == 0)
		{
			System.out.println("I see you want to sign up for Bank of R, what would you like your username to be?");
			String uName = reader.nextLine();
			if(uName.length() > 16)
				System.out.println("Usernames can only be 16 characters or less, please try again!(-1 to exit)");
			else if(uName.length() == 0)
				System.out.println("Usernames must have more than 0 characters, please try again!(-1 to exit)");
			else if(uName.equals("-1"))
				return false;
			else
			{
				int safe = 0;
				for(int i = 0; i < uName.length(); i++)
				{
					if(!(!Character.isLetter(uName.charAt(i)) ^ !Character.isDigit(uName.charAt(i))))
					{
						safe = 1;
						break;
					}
				}
				if(safe == 0)
				{
					//If user is not a duplicate
					if(userList.findUser(uName) == -1)
					{
						while(running == 0)
						{
							System.out.println("Now What would your password be? ");
							String password = reader.nextLine();
							if(password.length() < 6)
								System.out.println("Sorry passwords must be more than 6 characters, please try again!(-1 to exit)");
							else if(password.equals("-1"))
								return false;
							else
							{
								System.out.println("Great! You're all set!");
								User newUser = new User(uName, password);
								userList.add(newUser);
								return true;
							}
						}	
					}
					else
						System.out.println("Sorry an account with that name already exists");
				}
				else
					System.out.println("Sorry you can only have numerical or alphabetical symbols in your username, please try again!");

			}
				
		}
		return false;
		
	}

	
	
}
