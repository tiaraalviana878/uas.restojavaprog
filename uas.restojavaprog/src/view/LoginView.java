package view;

import controller.MainController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView extends VBox {

    public LoginView(Stage stage) {
        setSpacing(12);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(30));

        //ganti warna//
        setStyle("-fx-background-color: #667e70ff;");


        Label title = new Label("Login");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: 700;");

        TextField username = new TextField();
        username.setPromptText("Username");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        Label error = new Label();
        error.setStyle("-fx-text-fill: red;");

        Button loginBtn = new Button("Login");
        loginBtn.setOnAction(e -> {
            String u = username.getText();
            String p = password.getText();

            if (u.equals("4'restaurant") && p.equals("1234")) {
                // setelah berhasil login → buka MainView
                MainController controller = new MainController();
                MainView main = new MainView(controller);
                controller.setView(main);

                Scene scene = new Scene(main, 1100, 700);
                scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

                stage.setScene(scene);
            } else {
                error.setText("Username atau password salah!");
            }
        });

        getChildren().addAll(title, username, password, loginBtn, error);
    }
}
