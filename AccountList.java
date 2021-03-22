import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
			file.createNewFile();
			reader = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
			userName = reader.nextLine();
		}
	}
	
	
	public void save() throws IOException
	{
		try {
			writer = new FileWriter(file, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = 0;
		for(int i = 0; i< accountList.size(); i++)
		{
			if(count == 0)
			{
				writer.write(accountList.get(i).getuName());
				count++;
			}
			if(count == 1)
			{
				writer.write(accountList.get(i).getPassword());
				count++;
			}
			if(count == 2)
			{
				writer.write("\n");
				count = 0;
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
	public void delete(String uName)
	{
		int trgtUser = findUser(uName);
		if(trgtUser != -1)
		{
			accountList.remove(trgtUser);
			System.out.println("User " + uName + " has been deleted successfully");
		}
		else
			System.out.println("User does not exist.");
		
	}
	//-------Find User-------
	//@param userName and password or just username search available.
	//uName and pass search is used to verify identity and for log in only
	public User findUser(String uName, String pass)
	{
		for (User element : accountList)
			if (element.getuName().equals(uName))
				if(element.getPassword().equals(pass))
					return element;	   
		return null;
	}
	public int findUser(String uName)
	{
		for (User element : accountList)
			if (element.getuName().equals(uName))
					return accountList.indexOf(element);	   
		return -1;
	}
}
