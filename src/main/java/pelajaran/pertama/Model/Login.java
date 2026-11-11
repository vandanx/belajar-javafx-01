package pelajaran.pertama.Model;

public class Login {

    public static final int Username_Batas = 20;
    public static final int Password_Batas = 50;

    private String username;
    private String password;

    public Login(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username Tidak Boleh Kosong!");
        }
        if (username.length() > Username_Batas) {
            throw new IllegalArgumentException("Username Terlalu Panjang!");
        }
        this.username = username;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password Tidak Boleh Kosong!");
        }
        if (password.length() > Password_Batas) {
            throw new IllegalArgumentException("Password Terlalu Panjang!");
        }

        this.password = password;
    }
}
