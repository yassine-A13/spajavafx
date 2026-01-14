package spa;

public class client extends spa.user {
	public int age;
	public client (String nom , String cin , int telephone , String email , String password , String role) {
		super(nom,cin,telephone,email,password,role);
		
	}
	
	public int getAge() {
		return this.age;
	}

}