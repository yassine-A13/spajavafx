package spa;

public class gerant extends user{

	public gerant (String nom , String cin , int telephone , String email , String password ,String role) {
		super(nom,cin,telephone,email,password,role);
	}
	
	public void afficher() {
		System.out.println("je suis gerant");
	}
}