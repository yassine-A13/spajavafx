package spa.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    // Paramètres de connexion (à adapter si besoin)
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";

    private DbConnection() {}

    /**
     * Ouvre une connexion JDBC vers PostgreSQL.
     * Utilisation :
     * try (Connection conn = DbConnection.getConnection()) {
     *     // opérations JDBC...
     * }
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur connexion DB", e);
        }
    }
}
