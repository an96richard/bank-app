import java.awt.datatransfer.SystemFlavorMap;
import java.io.IOException;
import java.text.NumberFormat;
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
	 *  v0.46
	 *  Changes:
	 *  	-Fixed minor typos
	 *  	-Fixed withdraw problem
	 *  	-Fixed adding and removing admin privledges
	 *  	-Fixed incorrect loading of accounts
	 *  	-Fixed problem with changing usernames and passwords
	 *  
	 *  Notes: Lots of minor fixes mostly found from testing today. Good Haul. Will work on saving balance on accounts next.
	 *  
	 *  Todo:
	 *  	-Implement saving balance from users.
	 *  	-Implement better search functions to find accounts in large groups
	 *  	-Implement sorting methods to show list of accounts.
	 *  	-Implement database, multithreading, etc
	 *  	-Move all this to the readMe.
	 *  
	 *  v0.45
	 *  Changes:
	 *  	-Implemented Admin Menu
	 *  	-Implemented Manage Accounts Menu
	 *  		-Includes fully implemented functions of deleting accounts, managing balance, managing username + password, etc
	 *  	-Added Admin Class
	 *  		-Needs Admin Methods that will be added later
	 *  	-Minor Changes to User and AccountList 
	 *  NOTES:
	 *  	Really good progress today, worked on two really big methods. First local version should be done soon. 
	 *  
	 *  Todo:
	 *  	-Implement better search functions to find accounts in large groups
	 *  	-Implement sorting methods to show list of accounts.
	 *  	-Implement database, multithreading, etc
	 *  	-Move all this to the readMe.
	 *  
	 *  v0.3
	 *  Changes:
	 *  	-Added Main User Menu
	 *  		-Implemented Withdraw, Deposit, Check Balance options*
	 *  	-Added Admin tag onto users to check for admin priv
	 *  	-Added Admin sign ups
	 *  Notes: 
	 *  Found some motivation to code more, lets get this bread. 
	 *
	 *  Todo:
	 *  	-Finish AccountList class
	 *  	-Make admin menu (ability to delete accounts, move money freely, etc)
	 *  	-Implement database, multithreading, etc
	 *  	-Move all this to the readMe.
	 *  
	 *  
	 *  v0.22
	 *  Changes:
	 *  	-Minor fixes to the save method and read methods implemented in the AccountList
	 *  	-added a show list method to print the accounts currently saved. 
	 *  	
	 *  Notes:
	 *  slow day, dont feel like doing much, not a lot of motivation. 
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
	
	private static AccountList userList = new AccountList("accounts.txt", false);
	private static AccountList adminList = new AccountList("adminAccounts.txt", true);
	public static void main(String []args)
	{
		userList.showListAdmin(); //<----------------------This is used for Debug 
		int start = 0;
		Scanner reader = new Scanner(System.in);
		while(start == 0)
		{
			System.out.println("Welcome to Bank of R! What Can I Do For You?"
					+ "\n 1. Log In"
					+ "\n 2. Sign Up"
					+ "\n 0. Exit");
			
			String ans = reader.nextLine();
			if(ans.trim().equals("0") || ans.trim().toLowerCase().equals("exit"))
			{
				try {
					userList.save();
					adminList.save();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Thanks for using Bank of R! Have a great day!");
				start = 1;
			}
			
			
			//LOGIN OPTION
			else if(ans.trim().equals("1") || ans.trim().toLowerCase().equals("log in"))
			{
				User newUser = loginMenu();
				if(newUser != null)
				{
					if(newUser.getAdminPriv())
						mainAdminMenu(newUser);
					else
						mainUserMenu(newUser);
				}
			}
			//SIGN UP OPTION
			else if(ans.equals("2") || ans.toLowerCase().equals("sign up"))
			{
				signUpMenu();
				System.out.println("Thanks for Signing Up With Bank of R! Please Log In By Selecting Choice 1");
			}
			else
				System.out.println("Sorry that is not a valid choice, please try again!");
		}
	}
	
	
	/*=====================MAIN ADMIN MENU==================================
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public static void mainAdminMenu(User admin)
	{
		int active = 0;
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		Scanner reader = new Scanner(System.in);
		while(active ==0)
		{		
			System.out.println("Welcome, " + admin.getuName() + "! How Can I Assist You Today?"
					+ "\n 1. Deposit"
					+ "\n 2. Withdraw"
					+ "\n 3. Check Balance"
					+ "\n 4. Show User List"
					+ "\n 5. Show Admin List"
					+ "\n 6. Manage Accounts"
					+ "\n 0. Log Out");
			String answer = reader.nextLine();
			if(answer.trim().equals("1") || answer.trim().toLowerCase().equals("deposit"))
			{
				int running = 0;
				while(running == 0)
				{
					System.out.println("How Much Would You Like To Deposit? (Enter -1 To Go Back)");
					answer = reader.nextLine();
					double amount = 0.0;
					try 
					{
						amount = Double.parseDouble(answer);
					} 
					catch(Exception e)
					{
						System.out.println("That amount is invalid");
						continue;
					}
					
					
					if(amount < 0)
						running = 1;
					else
					{
						amount = Math.floor(amount * 100)/100;
						admin.deposit(amount);
						System.out.println("Deposit Successful! Your New Balance Is $" + admin.getBalance());
						running = 1;
					}
				}
			}
			if(answer.trim().equals("2") || answer.trim().toLowerCase().equals("withdraw"))
			{
				int running = 0;
				while(running == 0)
				{
					System.out.println("How Much Would You Like To Withdraw? (Enter -1 To Go Back)");
					answer = reader.nextLine();
					double amount = 0.0;
					try 
					{
							amount = Double.parseDouble(answer);
					} 
					catch(Exception e)
					{
						System.out.println("That amount is invalid");
						continue;
					}
					
					
					if(amount < 0)
						running = 1;
					else
					{
						amount = Math.floor(amount * 100)/100;
						if(admin.getBalance() >= amount)
						{
							admin.withdraw(amount);
							System.out.println("Withdraw Successful! Your New Balance Is $" + admin.getBalance());
							running = 1;
						}
						else
							System.out.println("Your Witdraw Cannot Be Executed Because You Do Not Have Enough Funds.");	
					}
				}
			}
			if(answer.trim().equals("3") || answer.trim().toLowerCase().equals("check balance"))
			{
				System.out.println("Your Balance: " + formatter.format(admin.getBalance()));
				continue;
			}
			if(answer.trim().equals("4") || answer.trim().toLowerCase().equals("show user list"))
			{
				userList.showListAdmin();
				continue;
			}
			if(answer.trim().equals("5") || answer.trim().toLowerCase().equals("show admin list"))
			{
				adminList.showListAdmin();
				continue;
			}
			if(answer.trim().equals("6") || answer.trim().toLowerCase().equals("manage accounts"))
			{
				int inMenu = 0;
				while(inMenu == 0)
				{
					System.out.println("Which Accounts would you like to manage?"
							+ "\n1. User"
							+ "\n2. Admin"
							+ "\n0. Go Back");
					
					answer = reader.nextLine();
					if(answer.trim().equals("1") || answer.trim().toLowerCase().equals("user"))
						manageAccounts(userList);
					else if(answer.trim().equals("2") || answer.trim().toLowerCase().equals("admin"))
						manageAccounts(adminList);
					else if(answer.trim().equals("0") || answer.trim().toLowerCase().equals("go back"))
						inMenu = 1;
					else
						System.out.println("Sorry that is not a valid list of users.");
				}
				continue;
			}
			if(answer.trim().equals("0") || answer.trim().toLowerCase().equals("log out"))
			{
				return;
			}
		}
		
	}
	
	/*=============================MAIN USER MENU==========================================
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public static void mainUserMenu(User user)
	{
		int active = 0;
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		Scanner reader = new Scanner(System.in);
		while(active ==0)
		{		
			System.out.println("Welcome, " + user.getuName() + "! How Can I Assist You Today?"
					+ "\n1. Deposit"
					+ "\n2. Withdraw"
					+ "\n3. Check Balance"
					+ "\n0. Log Out");
			String answer = reader.nextLine();
			if(answer.trim().equals("1") || answer.trim().toLowerCase().equals("deposit"))
			{
				int running = 0;
				while(running == 0)
				{
					System.out.println("How Much Would You Like To Deposit? (Enter -1 To Go Back)");
					answer = reader.nextLine();
					double amount = 0.0;
					try 
					{
						amount = Double.parseDouble(answer);
					} 
					catch(Exception e)
					{
						System.out.println("That amount is invalid");
						continue;
					}
					if(amount < 0)
					{
						running = 1;
					}
					else
					{
						amount = Math.floor(amount * 100)/100;
						user.deposit(amount);
						System.out.println("Deposit Successful! Your New Balance Is $" + user.getBalance());
						running = 1;
					}
					
				}
			}
			if(answer.trim().equals("2") || answer.trim().toLowerCase().equals("withdraw"))
			{
				int running = 0;
				while(running == 0)
				{
					System.out.println("How Much Would You Like To Withdraw? (Enter -1 To Go Back)");
					answer = reader.nextLine();
					double amount = 0.0;
					try 
					{
							amount = Double.parseDouble(answer);
					} 
					catch(Exception e)
					{
						System.out.println("That amount is invalid");
						continue;
					}
					if(amount < 0)
					{
						running = 1;
					}
					else
					{
						amount = Math.floor(amount * 100)/100;
						if(user.getBalance() >= amount)
						{
							user.withdraw(amount);
							System.out.println("Withdraw Successful! Your New Balance Is $" + user.getBalance());
							running = 1;
						}
						else
						{
							System.out.println("Your Withdraw Cannot Be Executed Because You Do Not Have Enough Funds.");
						}	
					}
					
				}
			}
			if(answer.trim().equals("3") || answer.trim().toLowerCase().equals("check balance"))
			{
				System.out.println("Your Balance: " + formatter.format(user.getBalance()));
				continue;
			}
			if(answer.trim().equals("0") || answer.trim().toLowerCase().equals("log out"))
			{
				return;
			}
		}
		
	}
	
	/*====================MANAGE ACCOUNTS MENU======================
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public static void manageAccounts(AccountList list)
	{
		int active = 0;
		Scanner reader = new Scanner(System.in);
		int numSelect = -2;
		while(active == 0)
		{
			list.showList();
			System.out.println("Which Account Would You Like To Alter? (Enter A Number. 0 To Exit. -1 To Show List)");
			try
			{
				numSelect = Integer.parseInt(reader.nextLine());
			}
			catch(Exception e)
			{
				System.out.println("That is not a valid number");
				continue;
			}
			if(numSelect < -1)
			{
				System.out.println("That is not a valid number");
				continue;
			}
			else if(numSelect == 0)
				return;
			else if(numSelect == -1)
				list.showList();
			else
			{
				User trgt = list.getUser(numSelect-1);
				int inMenu = 0;
				while(inMenu == 0)
				{
					System.out.println("What would you like to do?"
							+ "\n1. Delete Account"
							+ "\n2. Change Balance"
							+ "\n3. Change Password"
							+ "\n4. Change Username"
							+ "\n5. Change Admin Privledges"
							+ "\n0. Go Back");
					String answer = reader.nextLine();
					if(answer.trim().equals("1") || answer.trim().toLowerCase().equals("delete account"))
					{
						System.out.println("Are You Sure You Want to Delete " + trgt.getuName() + "? (Y/N)");
						answer = reader.nextLine();
						if( answer.trim().toLowerCase().equals("y") || answer.trim().toLowerCase().equals("yes"))
							list.delete(trgt.getuName());
						else
							continue;
					}
					if(answer.trim().equals("2") || answer.trim().toLowerCase().equals("change balance"))
					{
						boolean in = true;
						while(in)
						{
							System.out.println("What Would You Like To Do? "
									+ "\n- Withdraw"
									+ "\n+ Deposit"
									+ "\n0. Go Back");
							answer = reader.nextLine();
							if(answer.trim().equals("-") || answer.trim().toLowerCase().equals("withdraw"))
							{
								int running = 0;
								while(running == 0)
								{
									System.out.println("How Much Would You Like To Withdraw? (Enter -1 To Go Back)");
									answer = reader.nextLine();
									double amount = 0.0;
									try 
									{
											Double.parseDouble(answer);
									} 
									catch(Exception e)
									{
										System.out.println("That amount is invalid");
										continue;
									}
									if(amount < 0)
									{
										running = 1;
									}
									else
									{
										amount = Math.floor(amount * 100)/100;
										if(trgt.getBalance() > amount)
										{
											trgt.withdraw(amount);
											System.out.println("Withdraw Successful! The New Balance Is $" + trgt.getBalance());
											running = 1;
										}
										else
										{
											System.out.println("The Withdraw Cannot Be Executed Because The User Does Not Have Enough Funds.");
										}	
									}
								}
							}
							else if(answer.trim().equals("+") || answer.trim().toLowerCase().equals("deposit"))
							{
								int running = 0;
								while(running == 0)
								{
									System.out.println("How Much Would You Like To Deposit? (Enter -1 To Go Back)");
									answer = reader.nextLine();
									double amount = 0.0;
									try 
									{
										amount = Double.parseDouble(answer);
									} 
									catch(Exception e)
									{
										System.out.println("That amount is invalid");
										continue;
									}
									if(amount < 0)
										running = 1;
									else
									{
										amount = Math.floor(amount * 100)/100;
										trgt.deposit(amount);
										System.out.println("Deposit Successful! The New Balance Is $" + trgt.getBalance());
										running = 1;
									}
								}
							}
							else if(answer.trim().equals("0") || answer.trim().toLowerCase().equals("go back"))
								in = false;
							else
								System.out.println("That Is Not A Valid Choice");
						}
					}
					if(answer.trim().equals("3") || answer.trim().toLowerCase().equals("change password"))
					{
						int running = 0;
						while(running == 0)
						{
							System.out.println("What Is The New Password? ");
							String password = reader.nextLine();
							if(password.length() < 6)
								System.out.println("Sorry passwords must be more than 6 characters, please try again!(-1 to exit)");
							else if(password.equals("-1"))
								running = 1;
							else
							{
								trgt.setPassword(password);
								System.out.println("Password Successfully Changed");
								running = 1;
							}
						}
					}
					if(answer.trim().equals("4") || answer.trim().toLowerCase().equals("change username"))
					{
						int running = 0;
						while(running == 0)
						{
							System.out.println("What Is The New Username?");
							String uName = reader.nextLine();
							if(uName.length() > 16)
								System.out.println("Usernames can only be 16 characters or less, please try again!(-1 to exit)");
							else if(uName.length() == 0)
								System.out.println("Usernames must have more than 0 characters, please try again!(-1 to exit)");
							else if(uName.trim().equals("-1"))
								running = 1;
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
									if(userList.findUser(uName) == -1 && adminList.findUser(uName) == -1)
									{
										trgt.setuName(uName);
										System.out.println("Username Successfully Changed!");
										running = 1;
									}
									else
										System.out.println("Sorry an account with that name already exists");
								}
								else
									System.out.println("Sorry you can only have numerical or alphabetical symbols in your username, please try again!");
							}
						}
					}
					if(answer.trim().equals("5") || answer.trim().toLowerCase().equals("change admin privledges"))
					{
						int running = 0;
						while(running == 0)
						{
							if(trgt.getAdminPriv())
							{
								System.out.println("Would You Like To Remove This Account's Admin? (Y/N)");
								answer = reader.nextLine();
								if(answer.trim().equals("y") || answer.trim().toLowerCase().equals("yes"))
								{
									trgt.setAdminPriv(false);
									adminList.delete(trgt.getuName());
									userList.add(trgt);
								}
								running = 1;
							}
							else
							{
								System.out.println("Would You Like To Give This Account Admin? (Y/N)");
								answer = reader.nextLine();
								if(answer.trim().equals("y") || answer.trim().toLowerCase().equals("yes"))
								{
									trgt.setAdminPriv(true);
									adminList.add(trgt);
									userList.delete(trgt.getuName());
								}
								running = 1;
							}
						}
					}
					if(answer.trim().equals("0") || answer.trim().toLowerCase().equals("go back"))
						inMenu = 1;
				}
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
			trgtUser = adminList.findUser(username, password);
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
			else if(uName.trim().equals("-1"))
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
					if(userList.findUser(uName) == -1 && adminList.findUser(uName) == -1)
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
								User newUser = null;
								System.out.println("Is this an admin account? (Y/N)");
								String answer = reader.nextLine();
								if(answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("y"))
								{
									newUser = new Admin(uName, password);
									newUser.setAdminPriv(true);
									adminList.add(newUser);
								}
								else
								{
									newUser = new User(uName, password);
									userList.add(newUser);
								}
								System.out.println("Great! You're all set!");
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
