package view;

import controller.MainController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.MenuItem;
import model.OrderItem;

public class MainView extends BorderPane {
    private final ListView<MenuItem> menuList = new ListView<>();
    private final ListView<OrderItem> cartList = new ListView<>();
    private final Label totalLabel = new Label("Rp 0");
    private final MainController controller;

    public MainView(MainController controller) {
        this.controller = controller;
        buildLayout();
    }

    private void buildLayout() {
        // Header
        Label title = new Label("4'Restaurant");
        title.getStyleClass().add("title");
        StackPane header = new StackPane(title);
        header.setPadding(new Insets(20));
        header.setStyle("-fx-background-color: linear-gradient(#fff7ec, #95ab97ff);");
        setTop(header);

        // Menu list
        menuList.setCellFactory(lv -> new view.MenuCell(controller));
        menuList.setPrefWidth(680);

        VBox leftBox = new VBox(12, new Label("Menu"), menuList);
        leftBox.setPadding(new Insets(20));

        // Cart
        cartList.setCellFactory(lv -> new view.CartCell(controller));
        cartList.setPrefWidth(320);

        Label cartTitle = new Label("Order Summary");
        cartTitle.getStyleClass().add("title-small");

        Button confirm = new Button("Confirm Order");
        confirm.getStyleClass().add("confirm-button");
        confirm.setOnAction(e -> controller.confirmOrder());

        HBox totalRow = new HBox(10, new Label("Total:"), totalLabel, confirm);
        totalRow.setAlignment(Pos.CENTER_LEFT);
        totalRow.setPadding(new Insets(10));

        VBox rightBox = new VBox(12, cartTitle, cartList, totalRow);
        rightBox.setPadding(new Insets(20));
        rightBox.setStyle("-fx-background-color: transparent;");

        HBox main = new HBox(leftBox, rightBox);
        HBox.setHgrow(leftBox, Priority.ALWAYS);
        setCenter(main);
    }

    public void bindMenu(javafx.collections.ObservableList<MenuItem> items) {
        menuList.setItems(items);
    }

    public void bindCart(javafx.collections.ObservableList<OrderItem> items) {
        cartList.setItems(items);
    }

    public void setTotal(int amount) {
        totalLabel.setText("Rp " + String.format("%,d", amount));
    }

    public void refreshCart() {
        setTotal(controller.computeTotal());
        // force refresh
        cartList.refresh();
    }

    public void updateCart() {
        refreshCart();
    }

    public void showMessage(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
