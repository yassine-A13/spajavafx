package spa;

public class Services {
	public String nom;
	public double prix;
	
	public Services(String nom , double prix) {
		this.nom=nom;
		this.prix=prix;
	}

	public String getNom() {
		return nom;
	}

	public double getPrix() {
		return prix;
	}
	public String toLine() {
		return this.nom + ";" + this.prix ;
	}
	
	public static Services fromLine(String line) {
		String[] t = line.split(";");
		
		String nom = t[0];
		double prix = Double.parseDouble(t[1]);
		
		return new Services(nom,prix);
	}
}
