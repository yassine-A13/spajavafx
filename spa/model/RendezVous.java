package spa;

import java.time.LocalDate;

public class RendezVous {
	int id;
	public LocalDate date;
	public String nomClient;
	public String nomEmployee;
	public String nomService;
	public double prixTotal;

	
	public RendezVous(int id,LocalDate date , String nomClient,String nomEmployee,String nomService,double prixTotal) {
		this.id=id;
		this.date=date;
		this.nomClient=nomClient;
		this.nomEmployee=nomEmployee;
		this.nomService=nomService;
		this.prixTotal=prixTotal;

		
	}

	public int getId() { return id; }
	public LocalDate getDate() { return date; }
	public String getNomClient() { return nomClient; }
	public String getNomEmployee() { return nomEmployee; }
	public String getNomService() { return nomService; }
	public double getPrixTotal() { return prixTotal; }


	public String toLine() {
        return this.id + ";" + this.date + ";" + this.nomClient + ";" + this.nomEmployee + ";" + this.nomService + ";" + this.prixTotal;
    }
    

    public static RendezVous fromLine(String line) {
        String[] t = line.split(";");

        int id =Integer.parseInt(t[0]);
        LocalDate date = LocalDate.parse(t[1]);
        String nomClient = t[2];
        String nomEmployee = t[3];
        String nomService = t[4];
        double prixTotal =Double.parseDouble(t[5]);

        

        return new RendezVous(id, date, nomClient, nomEmployee, nomService, prixTotal);
    }
}
