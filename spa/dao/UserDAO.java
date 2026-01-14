package spa;

import spa.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    // Nom de la table (adapter si votre table a un autre nom)
    private static final String TABLE = "users";

    // ===== CREATE (INSERT) =====
    // Exemple simple : ajouter un utilisateur en base
    public static void create(user u) throws Exception {
        String sql = "INSERT INTO " + TABLE + " (cin, nom, role, telephone, email, password) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getCin());
            ps.setString(2, u.getNom());
            ps.setString(3, u.getRole());
            ps.setInt(4, u.getTel());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getPassword());

            ps.executeUpdate();
        }
    }

    // ===== READ ALL (SELECT) =====
    // Exemple simple : lire tous les utilisateurs
    public static List<user> getAll() throws Exception {
        String sql = "SELECT cin, nom, role, telephone, email, password FROM " + TABLE;
        List<user> users = new ArrayList<>();

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                users.add(mapRowToUser(rs));
            }
        }

        return users;
    }

    // ===== READ ONE (SELECT) =====
    // Exemple simple : chercher un utilisateur par CIN
    public static user getbycin(String cin) throws Exception {
        String sql = "SELECT cin, nom, role, telephone, email, password FROM " + TABLE + " WHERE cin = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cin);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUser(rs);
                }
            }
        }

        return null;
    }

    // ===== UPDATE =====
    // Exemple simple : modifier un utilisateur par CIN
    public static void updatebycin(String cin, user updateUser) throws Exception {
        String sql = "UPDATE " + TABLE + " SET nom = ?, role = ?, telephone = ?, email = ?, password = ? WHERE cin = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, updateUser.getNom());
            ps.setString(2, updateUser.getRole());
            ps.setInt(3, updateUser.getTel());
            ps.setString(4, updateUser.getEmail());
            ps.setString(5, updateUser.getPassword());
            ps.setString(6, cin);

            ps.executeUpdate();
        }
    }

    // ===== DELETE =====
    // Exemple simple : supprimer un utilisateur par CIN
    public static void deletebycin(String cin) throws Exception {
        String sql = "DELETE FROM " + TABLE + " WHERE cin = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cin);
            ps.executeUpdate();
        }
    }

    // Méthode utilitaire pour transformer une ligne SQL en objet user
    private static user mapRowToUser(ResultSet rs) throws SQLException {
        String cin = rs.getString("cin");
        String nom = rs.getString("nom");
        String role = rs.getString("role");
        int telephone = rs.getInt("telephone");
        String email = rs.getString("email");
        String password = rs.getString("password");

        return new user(nom, cin, telephone, email, password, role);
    }
}
