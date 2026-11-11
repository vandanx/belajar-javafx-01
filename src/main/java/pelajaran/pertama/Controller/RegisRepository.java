package pelajaran.pertama.Controller;

import java.sql.*;

import pelajaran.pertama.DatabaseConnection;
import pelajaran.pertama.Model.Login;

public class RegisRepository {
    private static final String SQL_INSERT = "INSERT INTO login (username, password) "
            + "VALUES (?, ?)";
    private static final String SQL_CEK = "SELECT 1 FROM login WHERE username = ?";

    public boolean simpan(Login login) throws SQLException {
        try (Connection koneksi = DatabaseConnection.getConnection();
                PreparedStatement ps = koneksi.prepareStatement(SQL_INSERT)) {
            ps.setString(1, login.getUsername());
            ps.setString(2, login.getPassword());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean usernameAda(String username) throws SQLException {
        try (Connection koneksi = DatabaseConnection.getConnection();
                PreparedStatement ps = koneksi.prepareStatement(SQL_CEK)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}