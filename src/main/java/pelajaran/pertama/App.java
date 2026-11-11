package pelajaran.pertama;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pelajaran.pertama.Controller.LoginRepository;
import pelajaran.pertama.Controller.RegisRepository;
import pelajaran.pertama.Service.LoginService;
import pelajaran.pertama.Service.RegisService;

public class App extends Application {

    private final LoginService loginService = new LoginService(new LoginRepository());
    private final RegisService regisService = new RegisService(new RegisRepository());

    private TextField usernameField;
    private PasswordField passwordField;

    @Override
    public void start(Stage stage) {
        Label judul = new Label("Login");
        judul.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(250);

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(250);
        passwordField.setOnAction(e -> handleLogin());

        Button tombolLogin = new Button("Login");
        tombolLogin.setOnAction(e -> handleLogin());

        Button tombolRegis = new Button("Registrasi");
        tombolRegis.setOnAction(e -> handleRegister());

        HBox tombol = new HBox(10, tombolLogin, tombolRegis);
        tombol.setAlignment(Pos.CENTER);

        VBox root = new VBox(12, judul, usernameField, passwordField, tombol);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 340, 300);
        stage.setTitle("Aplikasi Saya");
        stage.setScene(scene);
        stage.show();
    }

    private void handleLogin() {
        try {
            String hasil = loginService.masuk(usernameField.getText(), passwordField.getText());
            if (hasil.equals("OK")) {
                tampilkanPesan(Alert.AlertType.INFORMATION, "Login berhasil");
            } else {
                tampilkanPesan(Alert.AlertType.WARNING, hasil);
            }
        } catch (IllegalArgumentException e) {
            tampilkanPesan(Alert.AlertType.WARNING, e.getMessage());
        } catch (SQLException e) {
            tampilkanPesan(Alert.AlertType.ERROR, "Terjadi kesalahan database: " + e.getMessage());
        }
    }

    private void handleRegister() {
        try {
            String hasil = regisService.tambahAkun(usernameField.getText(), passwordField.getText());
            if (hasil.equals("OK")) {
                passwordField.clear();
                tampilkanPesan(Alert.AlertType.INFORMATION, "Registrasi berhasil, silakan login");
            } else {
                tampilkanPesan(Alert.AlertType.WARNING, hasil);
            }
        } catch (IllegalArgumentException e) {
            tampilkanPesan(Alert.AlertType.WARNING, e.getMessage());
        } catch (SQLException e) {
            tampilkanPesan(Alert.AlertType.ERROR, "Terjadi kesalahan database: " + e.getMessage());
        }
    }

    private void tampilkanPesan(Alert.AlertType tipe, String pesan) {
        Alert alert = new Alert(tipe);
        alert.setHeaderText(null);
        alert.setContentText(pesan);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}