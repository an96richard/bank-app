
public class Admin extends User
{
	
	public Admin(String uName, String password) 
	{
		super(uName, password);
		this.setAdminPriv(true);
		// TODO Auto-generated constructor stub
	}
	public Admin(String uName, String password, Double balance) 
	{
		super(uName, password, balance);
		this.setAdminPriv(true);
		// TODO Auto-generated constructor stub
	}

}
