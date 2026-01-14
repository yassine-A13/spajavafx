package spa;

import spa.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServicesDAO {

    private static final String TABLE = "services";

    // CREATE
    public static void ajouterService(Services s) throws Exception {
        String sql = "INSERT INTO " + TABLE + " (nom, prix) VALUES (?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getNom());
            ps.setDouble(2, s.getPrix());
            ps.executeUpdate();
        }
    }

    // READ ALL
    public static List<Services> getAll() throws Exception {
        String sql = "SELECT nom, prix FROM " + TABLE;
        List<Services> services = new ArrayList<>();

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nom = rs.getString("nom");
                double prix = rs.getDouble("prix");
                services.add(new Services(nom, prix));
            }
        }

        return services;
    }

    // DELETE
    public static void supprimerService(String nom) throws Exception {
        String sql = "DELETE FROM " + TABLE + " WHERE LOWER(nom) = LOWER(?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nom);
            ps.executeUpdate();
        }
    }
}
