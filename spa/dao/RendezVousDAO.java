package spa;

import spa.db.DbConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RendezVousDAO {
    // Adapter ces noms si votre table/colonnes ont des noms différents
    private static final String TABLE = "rendezvous";

    public static void create(RendezVous r) throws Exception {
        String sql = "INSERT INTO " + TABLE
                + " (id, date, nom_client, nom_employee, nom_service, prix_total) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, r.getId());
            ps.setDate(2, Date.valueOf(r.getDate()));
            ps.setString(3, r.getNomClient());
            ps.setString(4, r.getNomEmployee());
            ps.setString(5, r.getNomService());
            ps.setDouble(6, r.getPrixTotal());

            ps.executeUpdate();
        }
    }

    public static List<RendezVous> getAll() throws Exception {
        String sql = "SELECT id, date, nom_client, nom_employee, nom_service, prix_total FROM " + TABLE;
        List<RendezVous> rdvs = new ArrayList<>();

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                java.time.LocalDate date = rs.getDate("date").toLocalDate();
                String nomClient = rs.getString("nom_client");
                String nomEmployee = rs.getString("nom_employee");
                String nomService = rs.getString("nom_service");
                double prixTotal = rs.getDouble("prix_total");

                rdvs.add(new RendezVous(id, date, nomClient, nomEmployee, nomService, prixTotal));
            }
        }

        return rdvs;
    }

    public static void updatebyid(int id, RendezVous updateRendezVous) throws Exception {
        String sql = "UPDATE " + TABLE
                + " SET date = ?, nom_client = ?, nom_employee = ?, nom_service = ?, prix_total = ? WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(updateRendezVous.getDate()));
            ps.setString(2, updateRendezVous.getNomClient());
            ps.setString(3, updateRendezVous.getNomEmployee());
            ps.setString(4, updateRendezVous.getNomService());
            ps.setDouble(5, updateRendezVous.getPrixTotal());
            ps.setInt(6, id);

            ps.executeUpdate();
        }
    }

    public static void deletebyid(int id) throws Exception {
        String sql = "DELETE FROM " + TABLE + " WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
