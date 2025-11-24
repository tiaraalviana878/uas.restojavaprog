package view;

import controller.MainController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.MenuItem;

public class MenuCard extends VBox {

    public MenuCard(MenuItem item, MainController controller) {
        setSpacing(10);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(12));
        setPrefWidth(240);
        setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 2);");
        ImageView iv = new ImageView();
iv.setMouseTransparent(true); // <-- tambahkan ini
try {
    Image img = new Image(getClass().getResourceAsStream(item.getImagePath()), 220, 140, true, true);
    iv.setImage(img);
} catch (Exception ex) {
    // ignore
}

        iv.setFitWidth(220);
        iv.setFitHeight(140);
        Label name = new Label(item.getName());
        name.getStyleClass().add("menu-name");
        Label price = new Label("Rp " + String.format("%,d", item.getPrice()));
        price.getStyleClass().add("menu-price");
        Button add = new Button("+");
        add.getStyleClass().add("icon-button");
        add.setOnAction(e -> controller.addToCart(item));
        getChildren().addAll(iv, name, price, add);
    }
}
