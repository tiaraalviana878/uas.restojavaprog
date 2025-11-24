package view;

import controller.MainController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.MenuItem;

public class MenuCell extends ListCell<MenuItem> {
    private final HBox root = new HBox(12);
    private final ImageView imageView = new ImageView();
    private final VBox infoBox = new VBox(6);
    private final Label nameLbl = new Label();
    private final Label priceLbl = new Label();
    private final Button addBtn = new Button("+");

    private final MainController controller; // <-- SIMPAN controller

    public MenuCell(MainController controller) {
        this.controller = controller; // <-- FIX

        root.setPadding(new Insets(12));
        root.setStyle("-fx-background-color: white; -fx-background-radius: 10;");
        imageView.setFitWidth(96);
        imageView.setFitHeight(64);
        imageView.setPreserveRatio(true);

        nameLbl.getStyleClass().add("menu-name");
        priceLbl.getStyleClass().add("menu-price");
        addBtn.getStyleClass().add("icon-button");

        infoBox.getChildren().addAll(nameLbl, priceLbl);
        infoBox.setAlignment(Pos.CENTER_LEFT);

        HBox.setHgrow(new javafx.scene.layout.Region(), Priority.ALWAYS);
        root.getChildren().addAll(imageView, infoBox, addBtn);
    }

    @Override
    protected void updateItem(MenuItem item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null);
        } else {
            nameLbl.setText(item.getName());
            priceLbl.setText("Rp " + String.format("%,d", item.getPrice()));

            try {
                Image img = new Image(getClass().getResourceAsStream(item.getImagePath()), 96, 64, true, true);
                imageView.setImage(img);
            } catch (Exception ex) {
                imageView.setImage(null);
            }

            // --------------------------
            // FIX PALING PENTING
            // --------------------------
            addBtn.setOnAction(e -> controller.addToCart(item));

            setGraphic(root);
        }
    }
}
