package spa.utils;

import spa.db.DbConnection;
import java.sql.Connection;

public class TestDbConnection {
    public static void main(String[] args) {
        try (Connection c = DbConnection.getConnection()) {
            System.out.println("✅ Connected = " + (c != null && !c.isClosed()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
