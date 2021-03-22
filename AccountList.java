import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountList 
{
	private ArrayList<User> accountList = new ArrayList<User>();
	private Scanner reader;
	private FileWriter writer;
	private File file;
	
	//--------------Account List Constructor------------------
	//This should reader in a file that stores all accounts and update the arrayList with users and their information for use. 
	public AccountList()
	{	
		file = new File("accounts.txt");
		try {
			reader = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(reader.hasNext())
		{
			int count = 0;
			String userName = null;
			String password = null;
			while(count < 2 && reader.hasNext())
			{
				 if(count == 0)
					 userName = reader.nextLine();
				 else
					 password = reader.nextLine();
				 count++;
			}
			if(userName != null && password != null)
			{
				User user = new User(userName, password);
				accountList.add(user);
			}
		}
	}
	
	
	public boolean add(User newUser)
	{
		if(newUser != null)
		{
			accountList.add(newUser);
			return true;
		}
		else
			return false;
	}
	
	
	//-------Find User-------
	//@param userName and password or just username search available.
	//uName and pass search is used to verify identity and for log in only
	public User findUser(String uName)
	{
		for (User element : accountList)
			if (element.getuName().equals(uName))
					return element;	   
		return null;
	}
	public User findUser(String uName, String pass)
	{
		for (User element : accountList)
			if (element.getuName().equals(uName))
				if(element.getPassword().equals(pass))
					return element;	   
		return null;
	}
}
