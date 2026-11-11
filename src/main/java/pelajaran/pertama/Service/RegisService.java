package pelajaran.pertama.Service;

import java.sql.SQLException;

import pelajaran.pertama.Controller.RegisRepository;
import pelajaran.pertama.Model.Login;
import pelajaran.pertama.ClassTambahan.*;;

public class RegisService {
    private final RegisRepository regisRepository;

    public RegisService(RegisRepository regisRepository) {
        this.regisRepository = regisRepository;
    }

    public String tambahAkun(String username, String password) throws SQLException {
        Login input = new Login(username, password);

        if (regisRepository.usernameAda(input.getUsername())) {
            return "Username sudah terdaftar";
        }

        Login akun = new Login(input.getUsername(), PasswordUtil.md5(input.getPassword()));

        if (!regisRepository.simpan(akun)) {
            return "Registrasi gagal, coba lagi";
        }
        return "OK";
    }
}