package view;

import controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

   @Override
public void start(Stage stage) {
    LoginView login = new LoginView(stage);

    Scene scene = new Scene(login, 400, 300);
    stage.setTitle("Login");
    stage.setScene(scene);
    stage.show();
}

    public static void main(String[] args) {
        launch(args);
    }
}
