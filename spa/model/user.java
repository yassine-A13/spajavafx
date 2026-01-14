package spa;

public class user {
    
    public String nom;
    public String cin;
    public String role;
    public int telephone;
    public String email;
    public String password;
    
    

    public user (String nom, String cin, int telephone, String email, String password, String role) {
        this.nom = nom;
        this.cin = cin;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String toLine() {
        return this.cin + ";" + this.nom + ";" + this.role + ";" + this.telephone + ";" + this.email + ";" + this.password;
    }

    public String getNom() {
        return nom;
    }

    public String getCin() {
        return cin;
    }

    public int getTel() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // ===== SETTERS (POUR MODIFIER) =====
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setTel(int tel) {
        this.telephone = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static user fromLine(String line) {

        String[] t = line.split(";");

        if (t.length != 6) {
            throw new IllegalArgumentException(
                    "Ligne utilisateur invalide : " + line
            );
        }

        String cin = t[0];
        String nom = t[1];
        String role = t[2];
        int telephone = Integer.parseInt(t[3]);
        String email = t[4];
        String password = t[5];

        return new user(nom, cin, telephone, email, password, role);
    }

}
