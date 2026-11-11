package pelajaran.pertama.Service;

import java.sql.SQLException;

import pelajaran.pertama.Controller.LoginRepository;
import pelajaran.pertama.Model.Login;
import pelajaran.pertama.ClassTambahan.*;

public class LoginService {
    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public String masuk(String username, String password) throws SQLException {
        Login input = new Login(username, password);

        Login akun = loginRepository.findByUsername(input.getUsername());
        if (akun == null) {
            return "Akun belum terdaftar, silakan registrasi terlebih dahulu";
        }

        String hashInput = PasswordUtil.md5(input.getPassword());
        if (!akun.getPassword().equals(hashInput)) {
            return "Password salah";
        }

        return "OK";
    }
}