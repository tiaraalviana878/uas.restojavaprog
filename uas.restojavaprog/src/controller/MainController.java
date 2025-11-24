package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MenuItem;
import model.OrderItem;
import view.MainView;

public class MainController {
    private MainView view;
    private final ObservableList<OrderItem> cart = FXCollections.observableArrayList();
    private final ObservableList<MenuItem> menu = FXCollections.observableArrayList();

    public MainController() {
        loadSampleMenu();
    }

    public void setView(MainView view) {
        this.view = view;
        view.bindMenu(menu);
        view.bindCart(cart);
    }

    private void loadSampleMenu() {
        menu.addAll(
            new MenuItem("Pasta Carbonara", 45000, "/images/pasta.jpg"),
            new MenuItem("Cheese Burger", 38000, "/images/burger.jpg"),
            new MenuItem("Matcha Latte", 25000, "/images/matcha.jpg"),
            new MenuItem("Fruit Salad", 30000, "/images/salad.jpg")
        );
    }

    public void addToCart(MenuItem item) {
        for (OrderItem oi : cart) {
            if (oi.getName().equals(item.getName())) {
                oi.increment();
                view.refreshCart();
                return;
            }
        }
        cart.add(new OrderItem(item));
        view.refreshCart();
    }

    public void removeOne(OrderItem orderItem) {
        orderItem.decrement();
        if (orderItem.getQuantity() <= 0) cart.remove(orderItem);
        view.refreshCart();
    }

    public int computeTotal() {
        return cart.stream().mapToInt(OrderItem::getTotalPrice).sum();
    }

    public void confirmOrder() {
        if (cart.isEmpty()) {
            view.showMessage("Keranjang kosong", "Silakan pilih menu terlebih dahulu.");
            return;
        }
        view.showMessage("Pesanan dikonfirmasi", "Total: Rp " + String.format("%,d", computeTotal()));
        cart.clear();
        view.refreshCart();
    }
}
