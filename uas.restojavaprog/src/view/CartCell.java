package view;

import controller.MainController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import model.OrderItem;

public class CartCell extends ListCell<OrderItem> {
    private final HBox root = new HBox(10);
    private final Label nameLbl = new Label();
    private final Label qtyLbl = new Label();
    private final Label priceLbl = new Label();
    private final Button minusBtn = new Button("-");
    private final Button removeBtn = new Button("x");
    private controller.MainController controller = null;

    public CartCell(controller.MainController controller) {
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: white; -fx-background-radius: 8;");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        root.setAlignment(Pos.CENTER_LEFT);
        root.getChildren().addAll(qtyLbl, nameLbl, spacer, priceLbl, minusBtn, removeBtn);

        minusBtn.setOnAction(e -> {
            OrderItem oi = getItem();
            if (oi != null && controller != null) controller.removeOne(oi);
        });
        removeBtn.setOnAction(e -> {
            OrderItem oi = getItem();
            if (oi != null && controller != null) controller.removeOne(oi);
        });
    }

    @Override
    protected void updateItem(OrderItem item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
        } else {
            qtyLbl.setText(String.valueOf(item.getQuantity()));
            nameLbl.setText(item.getName());
            priceLbl.setText("Rp " + String.format("%,d", item.getTotalPrice()));
            setGraphic(root);
        }
    }
}
