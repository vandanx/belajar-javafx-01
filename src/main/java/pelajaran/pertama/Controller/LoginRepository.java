package pelajaran.pertama.Controller;

import java.sql.*;

import pelajaran.pertama.DatabaseConnection;
import pelajaran.pertama.Model.*;

public class LoginRepository {
    public Login findByUsername(String username) throws SQLException {
        String sql = "SELECT username, password FROM login WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Login(rs.getString("username"), rs.getString("password"));
                }
            }
        }
        return null;
    }
}
