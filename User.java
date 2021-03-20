public class User 
{
	private String name, ID, password, uName;
	
	private double balance;
	public User(String uName, String password)
	{
		this.uName = uName; 
		this.password = password;
	}
	public String getuName() 
	{
		return uName;
	}
	public void setuName(String uName)
	{
		this.uName = uName;
	}
	public String getName()
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getID() 
	{
		return ID;
	}

	public void setID(String iD) 
	{
		ID = iD;
	}

	public double getBalance()
	{
		return balance;
	}

	public void setBalance(double balance) 
	{
		this.balance = balance;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
}
