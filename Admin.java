
public class Admin extends User
{
	
	public Admin(String uName, String password) 
	{
		super(uName, password);
		this.setAdminPriv(true);
		// TODO Auto-generated constructor stub
	}

}
