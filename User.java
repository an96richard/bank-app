public class User 
{
	private String password, uName;
	
	private double balance;
	public User(String uName, String password)
	{
		this.uName = uName; 
		this.password = password;
		this.balance = 0;
	}
	public String getuName() 
	{
		return uName;
	}
	public void setuName(String uName)
	{
		this.uName = uName;
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
